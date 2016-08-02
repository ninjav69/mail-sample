/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coza.dcmgroup.proposal;

import coza.dcmgroup.dao.LookupDAO;
import coza.dcmgroup.dao.ProposalDAO;
import coza.dcmgroup.entity.SystemVariable;
import coza.dcmgroup.entity.dto.ProposalEmailObligationDTO;
import coza.dcmgroup.entity.dto.ProposalHistoryDTO;
import coza.dcmgroup.entity.legacy.LegacyUser;
import coza.dcmgroup.util.Logger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ninjav
 */
public class EmailProposalSender implements ProposalSender {

    @Override
    public SendProposalResponse sendProposal(SendProposalRequest request) {
        List<String> creditorEmails = new ArrayList<>();
        // Get the catch email address

        LegacyUser user = request.getUser();

        boolean sendMail, manuallyUpdated, sendProposalViaSystem = false;

        String persistedProposalId = "";

        int deductionId;

        Date emailManuallySentDate = new Date();

        ArrayList<Integer> IdsOfRecordsToBeUpdatedForEmailSentViaSystem = new ArrayList<Integer>();
        ArrayList<String> obligationEmailAddressSentTo = new ArrayList<String>();

        String obligationEmailAddress;
        String bccEmail = request.getProposalCatchEmailAddress();

        /* get the information pertaining to the email being sent to the creditors */
        ProposalEmailObligationDTO emailProposalDetails = ProposalDAO.getEmailProposalDetails(request.getPayPlanProposalPersistId());

        String fromEmailAddress = sanitizeEmailAddress(ProposalDAO.getSenderInformation(request.getClientId()));
        String senderInfo = sanitizeEmailAddress(ProposalDAO.getSenderInformation(request.getClientId()));
        String dcEmailAddress = sanitizeEmailAddress(ProposalDAO.getDCEmailAddress(request.getClientId()));

        List<ProposalHistoryDTO> proposalHistory = request.getProposalHistory();
        for (ProposalHistoryDTO p : proposalHistory) {
            if (persistedProposalId.equals("")) {
                persistedProposalId = p.getPersistedProposalId();
            }

            sendMail = false;

            if (p.getSendMail() != null) {
                sendMail = p.getSendMail();
            }

            deductionId = 0;
            if (p.getDeductionId() != null) {
                deductionId = p.getDeductionId();
            }

            manuallyUpdated = false;
            if (p.getManuallyUpdated() != null) {
                manuallyUpdated = p.getManuallyUpdated();
                emailManuallySentDate = p.getManuallyUpdatedOn();
            }

            // perform the deduction workflow maintenance if the email is being sent via the system or manually
            if (sendMail || manuallyUpdated) {
                // DeductionworkFlowItems
                if (deductionId != 0) {
                    Logger.debug(" ************* deductionId = " + deductionId + " ********** sendMail " + sendMail + " manuallyUpdated " + manuallyUpdated);
                    ProposalDAO.performDeductionWorkFlowItemsMaintenance(deductionId, "", "", user.getUserId());
                }
            }

            if (sendMail && p.getType().equals("obligation")) {

                if (!sendProposalViaSystem) {
                    sendProposalViaSystem = true;
                }

                // This creditor needs to be added to the list that receives the email with the proposal attached
                obligationEmailAddress = sanitizeEmailAddress(ProposalDAO.getEmailAddressForObligation(deductionId));
                creditorEmails.add(obligationEmailAddress);
                //creditorEmailRecipientList += obligationEmailAddress + ";";
                IdsOfRecordsToBeUpdatedForEmailSentViaSystem.add(p.getId());
                obligationEmailAddressSentTo.add(obligationEmailAddress);
            }

            if (manuallyUpdated) {
                // if there has been a manually sent email for the obligation update the db
                ProposalDAO.updateObligationsEmailManuallySentDate(p.getId(), user.getUserId(), emailManuallySentDate);
            }

            // validate if we have to send the proposal to the client
            if (sendMail && p.getType().equals("client")) {
                String consumerEmail = sanitizeEmailAddress(emailProposalDetails.getConsumerEmail());

                if (consumerEmail != null && !consumerEmail.isEmpty()) {
                    String consumerEmailBody = (String) emailProposalDetails.getConsumerEmailBody();
                    consumerEmailBody = consumerEmailBody.replace("<Consumer Name and Surname>", emailProposalDetails.getConsumer());
                    consumerEmailBody = consumerEmailBody.replace("<DC Organisation Name>", emailProposalDetails.getOrganisation());

                    ProposalEmail message = new ProposalEmail();
                    message.senderInfo = senderInfo;
                    message.fromEmailAddress = fromEmailAddress;
                    message.toAddresses.add(consumerEmail);
                    message.toAddresses.add(dcEmailAddress);
                    message.bccAddresses.add(bccEmail);
                    message.subject = emailProposalDetails.getConsumerSubject();
                    message.bodyText = consumerEmailBody;
                    
                    sendProposalEmail(message, request.getClientId(), persistedProposalId, emailProposalDetails.getIdentityNumber());

                    /* persist the comms to the the consumer */
                    ProposalDAO.payPlanProposalConsumerCommunication(p.getId(), p.getPayPlanProposalPersistId(), user.getUserId(), consumerEmail);

                    ProposalDAO.updateClientsProposalSentDate(request.getClientId());
                }
            }
        }

        /* we need to send the proposal via email */
        if (sendProposalViaSystem) {
            ProposalEmail message = new ProposalEmail();
                    message.senderInfo = senderInfo;
                    message.fromEmailAddress = fromEmailAddress;
                    for (String creditorEmail : creditorEmails) {
                        if (emailAddressIsValid(creditorEmail)) {
                            message.toAddresses.add(sanitizeEmailAddress(creditorEmail));
                        }
                    }
                    message.toAddresses.add(dcEmailAddress);
                    message.bccAddresses.add(bccEmail);
                    message.subject = emailProposalDetails.getCreditorSubject();
                    message.bodyText = emailProposalDetails.getcreditorEmailBody();
            
            sendProposalEmail(message, request.getClientId(), persistedProposalId, emailProposalDetails.getIdentityNumber());
        }

        ProposalDAO.updateObligationsAfterEmailSentViaSystem(IdsOfRecordsToBeUpdatedForEmailSentViaSystem, user.getUserId(), obligationEmailAddressSentTo);

        List<ProposalHistoryDTO> resultDTO = ProposalDAO.getProposalHistoryDetail(request.getPayPlanProposalPersistId());

        SendProposalResponse response = new SendProposalResponse();

        response.setResult(resultDTO);

        return response;
    }

    private static void sendProposalEmail(ProposalEmail message, Integer clientId, String persistedProposalId, String identityNumber) {
        println("Faking send of proposal:");
        println("Sender[" + message.senderInfo + "]");
        println("From[" + message.fromEmailAddress + "]");
        println("To[" + ProposalEmail.formatMultipleAddresses(message.toAddresses) + "]");
        println("BCC[" + ProposalEmail.formatMultipleAddresses(message.bccAddresses) + "]");
        println("Subject[" + message.subject + "]");
        println("Body[" + message.bodyText + "]");
        println("ClientID[" + clientId + "]");
        println("PersistedProposalID[" + persistedProposalId + "]");
        println("IdentityNumber[" + identityNumber + "]");
    }

    private static class ProposalEmail {

        public String senderInfo;
        public String fromEmailAddress;
        public List<String> toAddresses = new ArrayList<>();
        public List<String> bccAddresses = new ArrayList<>();
        public String subject;
        public String bodyText;
    
        public static String formatMultipleAddresses(List<String> addresses) {
            return String.join(";", addresses);
        }
    }

    private String sanitizeEmailAddress(String emailAddress) {
        return emailAddress.trim().replace(";", "");
    }
    
    private boolean emailAddressIsValid(String emailAddress) {
        if (emailAddress != null && !emailAddress.isEmpty() && !emailAddress.contains(";")) {
            return true;
        }
        return false;
    }

    private static void println(String text) {
        Logger.debug("~~~~~" + text);
    }
}
