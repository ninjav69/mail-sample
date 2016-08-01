package coza.dcmgroup.dao;

import coza.dcmgroup.entity.dto.ProposalHistoryDTO;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import coza.dcmgroup.entity.dto.ProposalEmailObligationDTO;
import coza.dcmgroup.util.JPA;
import coza.dcmgroup.util.Logger;

public class ProposalDAO {

    // mark
    public static List<ProposalHistoryDTO> getProposalHistoryDetail(int payPlanProposalPersistId) {
        /* get the history obligation records for this Proposal history record */
        StoredProcedureQuery spProposalHistoryDetails = JPA.em().createStoredProcedureQuery("dbo.sp_ProposalHistoryPopulate");
        spProposalHistoryDetails.registerStoredProcedureParameter("PayPlanProposalPersistId", Integer.class, ParameterMode.IN);
        spProposalHistoryDetails.setParameter("PayPlanProposalPersistId", (Integer) payPlanProposalPersistId);

        List<Object[]> proposalHistoryDetailList = spProposalHistoryDetails.getResultList();
        Logger.debug("Get Details for the persisted proposal record... PayPlanProposalPersistId = " + payPlanProposalPersistId);
        List<ProposalHistoryDTO> proposalHistoryDetailListValues = new ArrayList();
        ProposalHistoryDTO proposalHistoryObligationDetails = null;

        for (Object[] historyObligationResult : proposalHistoryDetailList) {
            proposalHistoryObligationDetails = new ProposalHistoryDTO();
            proposalHistoryObligationDetails.setId((Integer) historyObligationResult[0]);
            proposalHistoryObligationDetails.setPayPlanProposalPersistId((Integer) historyObligationResult[1]);
            proposalHistoryObligationDetails.setObligation((String) historyObligationResult[2]);
            proposalHistoryObligationDetails.setAccountNumber((String) historyObligationResult[3]);
            proposalHistoryObligationDetails.setEmailSystemSentDate((Date) historyObligationResult[4]);
            proposalHistoryObligationDetails.setEmailManuallySentDate((Date) historyObligationResult[5]);
            proposalHistoryObligationDetails.setSentBy((String) historyObligationResult[6]);
            proposalHistoryObligationDetails.setDeductionId((Integer) historyObligationResult[7]);
            proposalHistoryObligationDetails.setPersistedProposalId((String) historyObligationResult[8]);
            proposalHistoryObligationDetails.setType((String) historyObligationResult[9]);
            proposalHistoryObligationDetails.setManuallyUpdated((Boolean) historyObligationResult[10]);
            proposalHistoryObligationDetails.setConsumerEmailAddress((String) historyObligationResult[11]);

            Logger.debug(" Type = " + proposalHistoryObligationDetails.getType() + " ----  ConsumerEmailAddress" + proposalHistoryObligationDetails.getConsumerEmailAddress());

            proposalHistoryDetailListValues.add(proposalHistoryObligationDetails);
        }

        return proposalHistoryDetailListValues;
    }

    // mark
    public static String getEmailAddressForObligation(Integer deductionId) {
        /* get the email address for the deducitonid*/
        StoredProcedureQuery sp = JPA.em().createStoredProcedureQuery("dbo.sp_GetEmailAddressForDeductionId");
        sp.registerStoredProcedureParameter("DeductionID", Integer.class, ParameterMode.IN);
        sp.setParameter("DeductionID", (Integer) deductionId);

        List<String> emailAddresses = sp.getResultList();
        String emailAddressforDeductionId = "";

        for (String emailAddress : emailAddresses) {
            emailAddressforDeductionId = emailAddress;
        }
        return emailAddressforDeductionId;
    }

    // mark
    public static ProposalEmailObligationDTO getEmailProposalDetails(int payPlanProposalPersistId) {
        // variables used
        String creditorSubject = "",
                creditorEmailBody = "",
                EmailAddress = "";

        int stageId = 0,
                dcrs = 0;

        ProposalEmailObligationDTO proposalEmailObligationInfo = new ProposalEmailObligationDTO();

        // Get the consumer and DC info
        StoredProcedureQuery spGetConsumerAndDCInfo = JPA.em().createStoredProcedureQuery("dbo.sp_GetConsumerAndDCInfoForProposalToCreditors");
        spGetConsumerAndDCInfo.registerStoredProcedureParameter("PayPlanProposalPersistId", Integer.class, ParameterMode.IN);
        spGetConsumerAndDCInfo.setParameter("PayPlanProposalPersistId", (Integer) payPlanProposalPersistId);

        Logger.debug("********************* PayPlanProposalPersistId = " + payPlanProposalPersistId);

        List<Object[]> resultsConsumerAndDC = spGetConsumerAndDCInfo.getResultList();

        for (Object[] resultConsumerAndDC : resultsConsumerAndDC) {
            proposalEmailObligationInfo.setConsumer((String) resultConsumerAndDC[0]);
            proposalEmailObligationInfo.setIdentityNumber((String) resultConsumerAndDC[1]);
            proposalEmailObligationInfo.setNCRRegistrationNumber((String) resultConsumerAndDC[2]);
            proposalEmailObligationInfo.setOrganisation((String) resultConsumerAndDC[3]);
            proposalEmailObligationInfo.setContactName((String) resultConsumerAndDC[4]);
            proposalEmailObligationInfo.setTelephoneNumber((String) resultConsumerAndDC[5]);
            if (resultConsumerAndDC[6] != null) {
                proposalEmailObligationInfo.setConsumerEmail((String) resultConsumerAndDC[6] + ";");
            } else {
                proposalEmailObligationInfo.setConsumerEmail(";");
            }

            proposalEmailObligationInfo.setPersistedProposalId((String) resultConsumerAndDC[7]);
            stageId = (Integer) resultConsumerAndDC[8];
            dcrs = (Integer) resultConsumerAndDC[9];
        }

        // get the details for the email to the CREDITORS
        StoredProcedureQuery spProposalEmailInfo = JPA.em().createStoredProcedureQuery("dbo.sp_GetCreditorProposalEmailElements");
        spProposalEmailInfo.registerStoredProcedureParameter("DCRS", Boolean.class, ParameterMode.IN);
        spProposalEmailInfo.setParameter("DCRS", dcrs == 0 ? false : true);
        spProposalEmailInfo.registerStoredProcedureParameter("DCRSStageId", Integer.class, ParameterMode.IN);
        spProposalEmailInfo.setParameter("DCRSStageId", stageId);

        Logger.debug("################################# DCRS = " + dcrs + " stageId " + stageId);

        List<Object[]> results = spProposalEmailInfo.getResultList();

        for (Object[] result : results) {
            if (result[0].equals("Subject")) {
                creditorSubject = (String) result[1];
            }
            if (result[0].equals("Body")) {
                creditorEmailBody = (String) result[1];
            }
        }

        // Build up the email with the values specific for the consumer
        creditorSubject = creditorSubject.replace("<Consumer Id>", proposalEmailObligationInfo.getIdentityNumber());
        creditorSubject = creditorSubject.replace("<Consumer Name>", proposalEmailObligationInfo.getConsumer());
        creditorSubject = creditorSubject.replace("<NCR Number>", proposalEmailObligationInfo.getNCRRegistrationNumber());

        proposalEmailObligationInfo.setCreditorSubject(creditorSubject);

        creditorEmailBody = creditorEmailBody.replace("<DC Organisation Name>", proposalEmailObligationInfo.getOrganisation());
        creditorEmailBody = creditorEmailBody.replace("<NCR Number>", proposalEmailObligationInfo.getNCRRegistrationNumber());
        creditorEmailBody = creditorEmailBody.replace("<DC Principal Name>", proposalEmailObligationInfo.getContactName());
        creditorEmailBody = creditorEmailBody.replace("<DC contact Number>", proposalEmailObligationInfo.getTelephoneNumber());
        creditorEmailBody = creditorEmailBody.replace("<Consumer Id>", proposalEmailObligationInfo.getIdentityNumber());

        proposalEmailObligationInfo.setcreditorEmailBody(creditorEmailBody);

        // now get the email addresses for all the deductions that we are sending the email to
        StoredProcedureQuery spGetObligationEmailAddressInfo = JPA.em().createStoredProcedureQuery("dbo.sp_GetObligationEmailAddressesForProposal");
        spGetObligationEmailAddressInfo.registerStoredProcedureParameter("PayPlanProposalPersistId", Integer.class, ParameterMode.IN);
        spGetObligationEmailAddressInfo.setParameter("PayPlanProposalPersistId", (Integer) payPlanProposalPersistId);
        List<Object[]> resultsObligations = spGetObligationEmailAddressInfo.getResultList();

        ArrayList<Integer> PayPlanProposalPersistObligationCommunicationIds = new ArrayList<Integer>();
        ArrayList<String> EmailAddresses = new ArrayList<String>();

        for (Object[] resultObligation : resultsObligations) {
            EmailAddress += (String) resultObligation[0] + ";";
            PayPlanProposalPersistObligationCommunicationIds.add((Integer) resultObligation[1]);
        }

        proposalEmailObligationInfo.setEmailAddress(EmailAddress);

        // get the details for the email
        StoredProcedureQuery spProposalEmailInfoForConsumer = JPA.em().createStoredProcedureQuery("dbo.sp_GetConsumerProposalEmailElements");
        List<Object[]> resultsProposalEmailInfoForConsumer = spProposalEmailInfoForConsumer.getResultList();

        for (Object[] resultProposalEmailInfoForConsumer : resultsProposalEmailInfoForConsumer) {
            if (resultProposalEmailInfoForConsumer[0].equals("Subject")) {
                proposalEmailObligationInfo.setConsumerSubject((String) resultProposalEmailInfoForConsumer[1]);
            }

            if (resultProposalEmailInfoForConsumer[0].equals("Body")) {
                proposalEmailObligationInfo.setConsumerEmailBody((String) resultProposalEmailInfoForConsumer[1]);
            }
        }

        return proposalEmailObligationInfo;
    }

    // mark
    public static void payPlanProposalConsumerCommunication(int id, int payPlanProposalPersistId, String updatedBy, String consumerEmail) {
        System.out.println("Consumer Communication: ");
        System.out.println("ID[" + id + "]");
        System.out.println("ProposalPersistID[" + payPlanProposalPersistId + "]");
        System.out.println("UpdatedBy[" + updatedBy + "]");
        System.out.println("ConsumerEmail[" + consumerEmail + "]");
    }

    // mark
    public static void updateClientsProposalSentDate(int clientId) {
        System.out.println("Update Proposal Sent Date: ");
        System.out.println("ClientID[" + clientId + "]");
    }

    // mark
    public static void updateObligationsEmailManuallySentDate(int id, String updatedBy, Date manuallySentDate) {
        System.out.println("Update Email Manually Sent Date: ");
        System.out.println("ID[" + id + "]");
        System.out.println("UpdatedBy[" + updatedBy + "]");
        System.out.println("ManuallySentDate[" + manuallySentDate + "]");
    }

    // mark
    public static void updateObligationsAfterEmailSentViaSystem(ArrayList<Integer> deductionIdsOfEmailSentViaSystem, 
            String updatedBy, ArrayList<String> obligationEmailAddressSentTo) {
        
        System.out.println("Update Obligation After Email Sent Via System");
        for (int i = 0; i < deductionIdsOfEmailSentViaSystem.size(); i++) {
            System.out.println("ID[" + deductionIdsOfEmailSentViaSystem.get(i));
            System.out.println("EmailSentBySystem[" + true + "]");
            System.out.println("EmailSentBy[" + updatedBy + "]");
            System.out.println("EmailAddressSentTo[" + obligationEmailAddressSentTo.get(i));
        }
    }

    // mark
    public static void performDeductionWorkFlowItemsMaintenance(int deductionId, String fileSent, String xmlData, String updatedBy) {

        System.out.println("DeductionID[" + deductionId + "]");
        System.out.println("WorkflowItemId[" + 3 + "]");
        System.out.println("FileSent[" + fileSent + "]");
        System.out.println("FileSentExtension[" + "PDF" + "]");
        System.out.println("XmlData[" + xmlData + "]");
        System.out.println("UpdatedBy[" + updatedBy + "]");
    }

    // mark
    public static String getSenderInformation(int clientId) {
        /* used to build up the string for the sender */
        StoredProcedureQuery sp = JPA.em().createStoredProcedureQuery("dbo.sp_GetProposalFromEmailAddress");
        sp.registerStoredProcedureParameter("ClientID", Integer.class, ParameterMode.IN);
        sp.setParameter("ClientID", clientId);

        List<String> senderInfo = sp.getResultList();

        String senderDetails = "";

        for (String sender : senderInfo) {
            senderDetails = sender;
        }
        return senderDetails;
    }

    // mark
    public static String getDCEmailAddress(int clientId) {
        /* used to get the Dc's email address */
        StoredProcedureQuery sp = JPA.em().createStoredProcedureQuery("dbo.sp_GetDCEmailAddress");

        sp.registerStoredProcedureParameter("ClientID", Integer.class, ParameterMode.IN);
        sp.setParameter("ClientID", clientId);

        System.out.println("clientId->" + clientId);

        List<String> senderInfo = sp.getResultList();

        String dcEmail = "";

        for (String sender : senderInfo) {
            dcEmail = sender;
        }
        return dcEmail;
    }
}
