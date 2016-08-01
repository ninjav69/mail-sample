/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coza.dcmgroup.op.proposal;

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
public class TestMailProposal {

    public static void proposalSubmissionUpdate(int clientId, int payPlanProposalPersistId) throws Exception {

        // Get the catch email address
        SystemVariable proposalCatchEmailAddress = LookupDAO.getSystemVariable("proposal.catch.email.address");

        LegacyUser user = createLegacyUser();
        String creditorEmailRecipientList = "";
        boolean sendMail, manuallyUpdated, sendProposalViaSystem = false;
        String persistedProposalId = "";
        int deductionId;
        Date emailManuallySentDate = new Date();
        ArrayList<Integer> IdsOfRecordsToBeUpdatedForEmailSentViaSystem = new ArrayList<Integer>();
        ArrayList<String> obligationEmailAddressSentTo = new ArrayList<String>();
        String obligationEmailAddress;
        String bccEmail = (String) proposalCatchEmailAddress.getValue();
        /* get the information pertaining to the email being sent to the creditors */
        ProposalEmailObligationDTO result = ProposalDAO.getEmailProposalDetails(payPlanProposalPersistId);
        String fromEmailAddress = ProposalDAO.getSenderInformation(clientId);
        String senderInfo = ProposalDAO.getSenderInformation(clientId);
        String dcEmailAddress = ProposalDAO.getDCEmailAddress(clientId);

        List<ProposalHistoryDTO> proposalHistory = createProposalHistory();

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
                obligationEmailAddress = ProposalDAO.getEmailAddressForObligation(deductionId);
                creditorEmailRecipientList += obligationEmailAddress + ";";
                IdsOfRecordsToBeUpdatedForEmailSentViaSystem.add(p.getId());
                obligationEmailAddressSentTo.add(obligationEmailAddress);
            }

            if (manuallyUpdated) {
                // if there has been a manually sent email for the obligation update the db
                ProposalDAO.updateObligationsEmailManuallySentDate(p.getId(), user.getUserId(), emailManuallySentDate);
            }

            // validate if we have to send the proposal to the client
            if (sendMail && p.getType().equals("client")) {
                if (!result.getConsumerEmail().equals("")) {

                    String consumerEmailBody = (String) result.getConsumerEmailBody();
                    consumerEmailBody = consumerEmailBody.replace("<Consumer Name and Surname>", result.getConsumer());
                    consumerEmailBody = consumerEmailBody.replace("<DC Organisation Name>", result.getOrganisation());

                    String consumerEmailAddress = result.getConsumerEmail();
                    // remove the last ; from the to list because the email sending fails if it is not removed
                    consumerEmailAddress = consumerEmailAddress.substring(0, consumerEmailAddress.length() - 1);

                    SendProposalEmail(senderInfo, fromEmailAddress, result.getConsumerEmail() + dcEmailAddress, bccEmail, result.getConsumerSubject(), consumerEmailBody, clientId, persistedProposalId, result.getIdentityNumber());

                    /* persist the comms to the the consumer */
                    ProposalDAO.payPlanProposalConsumerCommunication(p.getId(), p.getPayPlanProposalPersistId(), user.getUserId(), consumerEmailAddress);

                    ProposalDAO.updateClientsProposalSentDate(clientId);
                }
            }
        }

        /* we need to send the proposal via email */
        if (sendProposalViaSystem) {
            SendProposalEmail(senderInfo, fromEmailAddress,
                    creditorEmailRecipientList + dcEmailAddress, bccEmail,
                    result.getCreditorSubject(),
                    result.getcreditorEmailBody(),
                    clientId, persistedProposalId, result.getIdentityNumber());
        }

        ProposalDAO.updateObligationsAfterEmailSentViaSystem(IdsOfRecordsToBeUpdatedForEmailSentViaSystem, user.getUserId(), obligationEmailAddressSentTo);

        List<ProposalHistoryDTO> resultDTO = ProposalDAO.getProposalHistoryDetail(payPlanProposalPersistId);
    }

    private static void SendProposalEmail(String sender, String fromAddress, String toAddress, String bccAddress, String subject, String bodyText, Integer clientId, String persistedProposalId, String identityNumber) throws Exception {
        System.out.println("Faking send of proposal:");
        System.out.println("Sender[" + sender + "]");
        System.out.println("From[" + fromAddress + "]");
        System.out.println("To[" + toAddress + "]");
        System.out.println("BCC[" + bccAddress + "]");
        System.out.println("Subject[" + subject + "]");
        System.out.println("Body[" + bodyText + "]");
        System.out.println("ClientID[" + clientId + "]");
        System.out.println("PersistedProposalID[" + persistedProposalId + "]");
        System.out.println("IdentityNumber[" + identityNumber + "]");
    } 

    private static LegacyUser createLegacyUser() {
        LegacyUser u = new LegacyUser("UserID-01", "userName", "firstName", "surname");
        return u;
    }

    private static List<ProposalHistoryDTO> createProposalHistory() {
        throw new UnsupportedOperationException("Find out what is sent via POST...");
    }
}
