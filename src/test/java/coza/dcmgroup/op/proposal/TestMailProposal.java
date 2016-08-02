/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coza.dcmgroup.op.proposal;

import coza.dcmgroup.dao.LookupDAO;
import coza.dcmgroup.entity.SystemVariable;
import coza.dcmgroup.entity.dto.ProposalHistoryDTO;
import coza.dcmgroup.entity.legacy.LegacyUser;
import coza.dcmgroup.proposal.EmailProposalSender;
import coza.dcmgroup.proposal.ProposalSender;
import coza.dcmgroup.util.Logger;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import coza.dcmgroup.proposal.SendProposalRequest;
import coza.dcmgroup.proposal.SendProposalResponse;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 *
 * @author ninjav
 */
public class TestMailProposal {

    private static void println(String text) {
        Logger.debug("~~~~~" + text);
    }

    // Test: saveAndSendProposalInfo(118880, 1302)
    
    // Prod: saveAndSendProposalInfo(183384, 22481)
    
    @Test
    public void testSendProposalEmail() throws Exception {
        SystemVariable proposalCatchEmailAddress = LookupDAO.getSystemVariable("proposal.catch.email.address");
        SendProposalRequest request = new SendProposalRequest();
        request.setUser(createLegacyUser());
        request.setClientId(118880);
        request.setPayPlanProposalPersistId(1302);
        request.setProposalCatchEmailAddress((String)proposalCatchEmailAddress.getValue());
        request.setProposalHistory(createProposalHistory());
        
        ProposalSender sender = new EmailProposalSender();
        SendProposalResponse response = sender.sendProposal(request);
        assertThat(response.getResult(), is(notNullValue()));
    }
    
    private static LegacyUser createLegacyUser() {
        LegacyUser u = new LegacyUser("UserID-01", "userName", "firstName", "surname");
        return u;
    }

    // Prod data
    private static List<ProposalHistoryDTO> createProposalHistory() {
        List<ProposalHistoryDTO> results = new ArrayList<>();
        
        ProposalHistoryDTO p = new ProposalHistoryDTO();
        p.setPayPlanProposalPersistId(22481);
        p.setObligation("Attorneys/Prokureurs Steenkamp (Zwartkops)");
        p.setAccountNumber("ADD");
        p.setSentBy("Lucinda Pegasus User DCM");
        p.setEmailSystemSentDate(null); //1469022825390
        p.setEmailManuallySentDate(null);
        p.setId(119008);
        p.setDeductionId(1123544);
        p.setSendMail(true);
        p.setManuallyUpdated(false);
        p.setManuallyUpdatedOn(null);
        p.setPersistedProposalId("8EDB862C-2168-4A46-A978-FFACD04C3441");
        p.setType("obligation");
        p.setConsumerEmailAddress(null);
        results.add(p);

        p = new ProposalHistoryDTO();
        p.setPayPlanProposalPersistId(22481);
        p.setObligation("Capfin");
        p.setAccountNumber("9595919");
        p.setSentBy("Lucinda Pegasus User DCM");
        p.setEmailSystemSentDate(null); //1469022825393
        p.setEmailManuallySentDate(null);
        p.setId(119009);
        p.setDeductionId(1124059);
        p.setSendMail(true);
        p.setManuallyUpdated(false);
        p.setManuallyUpdatedOn(null);
        p.setPersistedProposalId("8EDB862C-2168-4A46-A978-FFACD04C3441");
        p.setType("obligation");
        p.setConsumerEmailAddress(null);
        results.add(p);
        
        p = new ProposalHistoryDTO();
        p.setPayPlanProposalPersistId(22481);
        p.setObligation("Edcon - Edgars / Jet");
        p.setAccountNumber("7006600100072265384");
        p.setSentBy("Lucinda Pegasus User DCM");
        p.setEmailSystemSentDate(null); //1469022825397
        p.setEmailManuallySentDate(null);
        p.setId(119006);
        p.setDeductionId(1112278);
        p.setSendMail(true);
        p.setManuallyUpdated(false);
        p.setManuallyUpdatedOn(null);
        p.setPersistedProposalId("8EDB862C-2168-4A46-A978-FFACD04C3441");
        p.setType("obligation");
        p.setConsumerEmailAddress(null);
        results.add(p);
        
        p = new ProposalHistoryDTO();
        p.setPayPlanProposalPersistId(22481);
        p.setObligation("Hartbeesfontein Cash Store cc");
        p.setAccountNumber("7502135035085");
        p.setSentBy("Lucinda Pegasus User DCM");
        p.setEmailSystemSentDate(null); //1469022825400
        p.setEmailManuallySentDate(null);
        p.setId(119003);
        p.setDeductionId(1106908);
        p.setSendMail(true);
        p.setManuallyUpdated(false);
        p.setManuallyUpdatedOn(null);
        p.setPersistedProposalId("8EDB862C-2168-4A46-A978-FFACD04C3441");
        p.setType("obligation");
        p.setConsumerEmailAddress(null);
        results.add(p);


        p = new ProposalHistoryDTO();
        p.setPayPlanProposalPersistId(22481);
        p.setObligation("Isidingo Financial Services");
        p.setAccountNumber("ANNARIE/D274/2");
        p.setSentBy("Lucinda Pegasus User DCM");
        p.setEmailSystemSentDate(null); //1469022825400
        p.setEmailManuallySentDate(null);
        p.setId(119005);
        p.setDeductionId(1110103);
        p.setSendMail(true);
        p.setManuallyUpdated(false);
        p.setManuallyUpdatedOn(null);
        p.setPersistedProposalId("8EDB862C-2168-4A46-A978-FFACD04C3441");
        p.setType("obligation");
        p.setConsumerEmailAddress(null);
        results.add(p);

        p = new ProposalHistoryDTO();
        p.setPayPlanProposalPersistId(22481);
        p.setObligation("Lewis Group");
        p.setAccountNumber("058836722201");
        p.setSentBy("Lucinda Pegasus User DCM");
        p.setEmailSystemSentDate(null); //1469022825403
        p.setEmailManuallySentDate(null);
        p.setId(119001);
        p.setDeductionId(1106905);
        p.setSendMail(true);
        p.setManuallyUpdated(false);
        p.setManuallyUpdatedOn(null);
        p.setPersistedProposalId("8EDB862C-2168-4A46-A978-FFACD04C3441");
        p.setType("obligation");
        p.setConsumerEmailAddress(null);
        results.add(p);

        p = new ProposalHistoryDTO();
        p.setPayPlanProposalPersistId(22481);
        p.setObligation("Lewis Group");
        p.setAccountNumber("058836722202");
        p.setSentBy("Lucinda Pegasus User DCM");
        p.setEmailSystemSentDate(null); //1469022825407
        p.setEmailManuallySentDate(null);
        p.setId(119007);
        p.setDeductionId(1123543);
        p.setSendMail(true);
        p.setManuallyUpdated(false);
        p.setManuallyUpdatedOn(null);
        p.setPersistedProposalId("8EDB862C-2168-4A46-A978-FFACD04C3441");
        p.setType("obligation");
        p.setConsumerEmailAddress(null);
        results.add(p);

        p = new ProposalHistoryDTO();
        p.setPayPlanProposalPersistId(22481);
        p.setObligation("OK FURNITURE");
        p.setAccountNumber("400404725402");
        p.setSentBy("Lucinda Pegasus User DCM");
        p.setEmailSystemSentDate(null); //1469022825410
        p.setEmailManuallySentDate(null);
        p.setId(119002);
        p.setDeductionId(1106907);
        p.setSendMail(true);
        p.setManuallyUpdated(false);
        p.setManuallyUpdatedOn(null);
        p.setPersistedProposalId("8EDB862C-2168-4A46-A978-FFACD04C3441");
        p.setType("obligation");
        p.setConsumerEmailAddress(null);
        results.add(p);

        p = new ProposalHistoryDTO();
        p.setPayPlanProposalPersistId(22481);
        p.setObligation("Vehicle Finance - MFC (Motor Finance Corporation)");
        p.setAccountNumber("30407710012");
        p.setSentBy("Lucinda Pegasus User DCM");
        p.setEmailSystemSentDate(null); //1469022825410
        p.setEmailManuallySentDate(null);
        p.setId(119004);
        p.setDeductionId(1106909);
        p.setSendMail(true);
        p.setManuallyUpdated(false);
        p.setManuallyUpdatedOn(null);
        p.setPersistedProposalId("8EDB862C-2168-4A46-A978-FFACD04C3441");
        p.setType("obligation");
        p.setConsumerEmailAddress(null);
        results.add(p);

        p = new ProposalHistoryDTO();
        p.setPayPlanProposalPersistId(22481);
        p.setObligation(null);
        p.setAccountNumber(null);
        p.setSentBy("Lucinda Pegasus User DCM");
        p.setEmailSystemSentDate(null); //1469022823640
        p.setEmailManuallySentDate(null);
        p.setId(4416);
        p.setDeductionId(null);
        p.setSendMail(true);
        p.setManuallyUpdated(false);
        p.setManuallyUpdatedOn(null);
        p.setPersistedProposalId(null);
        p.setType("client");
        p.setConsumerEmailAddress("belindaduplessis975@gmail.com");
        results.add(p);

        return results;
    }
    
    /* Test data
    private static List<ProposalHistoryDTO> createProposalHistory() {
        List<ProposalHistoryDTO> results = new ArrayList<>();
        
        ProposalHistoryDTO p = new ProposalHistoryDTO();
        p.setPayPlanProposalPersistId(1302);
        p.setObligation("Attorneys/Prokureurs HTN (Bruma Finance Pty Ltd) ");
        p.setAccountNumber("1278429");
        p.setSentBy(null);
        p.setEmailSystemSentDate(null);
        p.setEmailManuallySentDate(null);
        p.setId(4848);
        p.setDeductionId(738986);
        p.setSendMail(null);
        p.setManuallyUpdated(false);
        p.setManuallyUpdatedOn(null);
        p.setPersistedProposalId("ACB093C0-249F-4B04-BF7D-7FE8CC2D799A");
        p.setType("obligation");
        p.setConsumerEmailAddress(null);
        results.add(p);

        p = new ProposalHistoryDTO();
        p.setPayPlanProposalPersistId(1302);
        p.setObligation("Vehicle Finance - Wesbank");
        p.setAccountNumber("85203571456");
        p.setSentBy(null);
        p.setEmailSystemSentDate(null);
        p.setEmailManuallySentDate(null);
        p.setId(4851);
        p.setDeductionId(738989);
        p.setSendMail(true);
        p.setManuallyUpdated(false);
        p.setManuallyUpdatedOn(null);
        p.setPersistedProposalId("ACB093C0-249F-4B04-BF7D-7FE8CC2D799A");
        p.setType("obligation");
        p.setConsumerEmailAddress(null);
        results.add(p);

        p = new ProposalHistoryDTO();
        p.setPayPlanProposalPersistId(1302);
        p.setObligation("Personal Loan - Wesbank");
        p.setAccountNumber("85216751922");
        p.setSentBy(null);
        p.setEmailSystemSentDate(null);
        p.setEmailManuallySentDate(null);
        p.setId(4850);
        p.setDeductionId(738988);
        p.setSendMail(true);
        p.setManuallyUpdated(false);
        p.setManuallyUpdatedOn(null);
        p.setPersistedProposalId("ACB093C0-249F-4B04-BF7D-7FE8CC2D799A");
        p.setType("obligation");
        p.setConsumerEmailAddress(null);
        results.add(p);

        return results;
    }
    */
}

/*
 Test data for: 118880
[{
	"payPlanProposalPersistId": 1302,
	"obligation": "Attorneys/Prokureurs HTN (Bruma Finance Pty Ltd) ",
	"accountNumber": "1278429",
	"sentBy": null,
	"emailSystemSentDate": null,
	"emailManuallySentDate": null,
	"id": 4848,
	"deductionId": 738986,
	"sendMail": null,
	"manuallyUpdated": false,
	"manuallyUpdatedOn": null,
	"persistedProposalId": "ACB093C0-249F-4B04-BF7D-7FE8CC2D799A",
	"type": "obligation",
	"consumerEmailAddress": null
}, {
	"payPlanProposalPersistId": 1302,
	"obligation": "Credit Card - ABSA",
	"accountNumber": "4550270337916014",
	"sentBy": null,
	"emailSystemSentDate": null,
	"emailManuallySentDate": null,
	"id": 4852,
	"deductionId": 738990,
	"sendMail": null,
	"manuallyUpdated": false,
	"manuallyUpdatedOn": null,
	"persistedProposalId": "ACB093C0-249F-4B04-BF7D-7FE8CC2D799A",
	"type": "obligation",
	"consumerEmailAddress": null
}, {
	"payPlanProposalPersistId": 1302,
	"obligation": "Credit Card - Nedbank",
	"accountNumber": "5898460800591281",
	"sentBy": null,
	"emailSystemSentDate": null,
	"emailManuallySentDate": null,
	"id": 4855,
	"deductionId": 738993,
	"sendMail": null,
	"manuallyUpdated": false,
	"manuallyUpdatedOn": null,
	"persistedProposalId": "ACB093C0-249F-4B04-BF7D-7FE8CC2D799A",
	"type": "obligation",
	"consumerEmailAddress": null
}, {
	"payPlanProposalPersistId": 1302,
	"obligation": "Easton Berry",
	"accountNumber": "0518140008001161558",
	"sentBy": null,
	"emailSystemSentDate": null,
	"emailManuallySentDate": null,
	"id": 4854,
	"deductionId": 738992,
	"sendMail": null,
	"manuallyUpdated": false,
	"manuallyUpdatedOn": null,
	"persistedProposalId": "ACB093C0-249F-4B04-BF7D-7FE8CC2D799A",
	"type": "obligation",
	"consumerEmailAddress": null
}, {
	"payPlanProposalPersistId": 1302,
	"obligation": "FNB - Smart Spend Personal loan",
	"accountNumber": "4000038962074",
	"sentBy": null,
	"emailSystemSentDate": null,
	"emailManuallySentDate": null,
	"id": 4849,
	"deductionId": 738987,
	"sendMail": null,
	"manuallyUpdated": false,
	"manuallyUpdatedOn": null,
	"persistedProposalId": "ACB093C0-249F-4B04-BF7D-7FE8CC2D799A",
	"type": "obligation",
	"consumerEmailAddress": null
}, {
	"payPlanProposalPersistId": 1302,
	"obligation": "JDG Trading",
	"accountNumber": "006365292040010166",
	"sentBy": null,
	"emailSystemSentDate": null,
	"emailManuallySentDate": null,
	"id": 4856,
	"deductionId": 738994,
	"sendMail": null,
	"manuallyUpdated": false,
	"manuallyUpdatedOn": null,
	"persistedProposalId": "ACB093C0-249F-4B04-BF7D-7FE8CC2D799A",
	"type": "obligation",
	"consumerEmailAddress": null
}, {
	"payPlanProposalPersistId": 1302,
	"obligation": "JDG Trading",
	"accountNumber": "0006365292600038771",
	"sentBy": null,
	"emailSystemSentDate": null,
	"emailManuallySentDate": null,
	"id": 4857,
	"deductionId": 738995,
	"sendMail": null,
	"manuallyUpdated": false,
	"manuallyUpdatedOn": null,
	"persistedProposalId": "ACB093C0-249F-4B04-BF7D-7FE8CC2D799A",
	"type": "obligation",
	"consumerEmailAddress": null
}, {
	"payPlanProposalPersistId": 1302,
	"obligation": "Personal loan - Nedbank",
	"accountNumber": "8001654086701",
	"sentBy": null,
	"emailSystemSentDate": null,
	"emailManuallySentDate": null,
	"id": 4853,
	"deductionId": 738991,
	"sendMail": true,
	"manuallyUpdated": false,
	"manuallyUpdatedOn": null,
	"persistedProposalId": "ACB093C0-249F-4B04-BF7D-7FE8CC2D799A",
	"type": "obligation",
	"consumerEmailAddress": null
}, {
	"payPlanProposalPersistId": 1302,
	"obligation": "Personal Loan - Wesbank",
	"accountNumber": "85216751922",
	"sentBy": null,
	"emailSystemSentDate": null,
	"emailManuallySentDate": null,
	"id": 4850,
	"deductionId": 738988,
	"sendMail": true,
	"manuallyUpdated": false,
	"manuallyUpdatedOn": null,
	"persistedProposalId": "ACB093C0-249F-4B04-BF7D-7FE8CC2D799A",
	"type": "obligation",
	"consumerEmailAddress": null
}, {
	"payPlanProposalPersistId": 1302,
	"obligation": "Vehicle Finance - Wesbank",
	"accountNumber": "85203571456",
	"sentBy": null,
	"emailSystemSentDate": null,
	"emailManuallySentDate": null,
	"id": 4851,
	"deductionId": 738989,
	"sendMail": true,
	"manuallyUpdated": false,
	"manuallyUpdatedOn": null,
	"persistedProposalId": "ACB093C0-249F-4B04-BF7D-7FE8CC2D799A",
	"type": "obligation",
	"consumerEmailAddress": null
}, {
	"payPlanProposalPersistId": 1302,
	"obligation": null,
	"accountNumber": null,
	"sentBy": null,
	"emailSystemSentDate": null,
	"emailManuallySentDate": null,
	"id": 0,
	"deductionId": null,
	"sendMail": null,
	"manuallyUpdated": false,
	"manuallyUpdatedOn": null,
	"persistedProposalId": null,
	"type": "client",
	"consumerEmailAddress": "carepremier.co.za@gmail.com"
}]
*/

/*
 Prod data for: saveAndSendProposalInfo(183384, 22481)
[{
	"payPlanProposalPersistId": 22481,
	"obligation": "Attorneys/Prokureurs Steenkamp (Zwartkops)",
	"accountNumber": "ADD",
	"sentBy": "Lucinda Pegasus User DCM",
	"emailSystemSentDate": 1469022825390,
	"emailManuallySentDate": null,
	"id": 119008,
	"deductionId": 1123544,
	"sendMail": null,
	"manuallyUpdated": false,
	"manuallyUpdatedOn": null,
	"persistedProposalId": "8EDB862C-2168-4A46-A978-FFACD04C3441",
	"type": "obligation",
	"consumerEmailAddress": null
}, {
	"payPlanProposalPersistId": 22481,
	"obligation": "Capfin",
	"accountNumber": "9595919",
	"sentBy": "Lucinda Pegasus User DCM",
	"emailSystemSentDate": 1469022825393,
	"emailManuallySentDate": null,
	"id": 119009,
	"deductionId": 1124059,
	"sendMail": null,
	"manuallyUpdated": false,
	"manuallyUpdatedOn": null,
	"persistedProposalId": "8EDB862C-2168-4A46-A978-FFACD04C3441",
	"type": "obligation",
	"consumerEmailAddress": null
}, {
	"payPlanProposalPersistId": 22481,
	"obligation": "Edcon - Edgars / Jet",
	"accountNumber": "7006600100072265384",
	"sentBy": "Lucinda Pegasus User DCM",
	"emailSystemSentDate": 1469022825397,
	"emailManuallySentDate": null,
	"id": 119006,
	"deductionId": 1112278,
	"sendMail": null,
	"manuallyUpdated": false,
	"manuallyUpdatedOn": null,
	"persistedProposalId": "8EDB862C-2168-4A46-A978-FFACD04C3441",
	"type": "obligation",
	"consumerEmailAddress": null
}, {
	"payPlanProposalPersistId": 22481,
	"obligation": "Hartbeesfontein Cash Store cc",
	"accountNumber": "7502135035085",
	"sentBy": "Lucinda Pegasus User DCM",
	"emailSystemSentDate": 1469022825400,
	"emailManuallySentDate": null,
	"id": 119003,
	"deductionId": 1106908,
	"sendMail": null,
	"manuallyUpdated": false,
	"manuallyUpdatedOn": null,
	"persistedProposalId": "8EDB862C-2168-4A46-A978-FFACD04C3441",
	"type": "obligation",
	"consumerEmailAddress": null
}, {
	"payPlanProposalPersistId": 22481,
	"obligation": "Isidingo Financial Services",
	"accountNumber": "ANNARIE/D274/2",
	"sentBy": "Lucinda Pegasus User DCM",
	"emailSystemSentDate": 1469022825400,
	"emailManuallySentDate": null,
	"id": 119005,
	"deductionId": 1110103,
	"sendMail": null,
	"manuallyUpdated": false,
	"manuallyUpdatedOn": null,
	"persistedProposalId": "8EDB862C-2168-4A46-A978-FFACD04C3441",
	"type": "obligation",
	"consumerEmailAddress": null
}, {
	"payPlanProposalPersistId": 22481,
	"obligation": "Lewis Group",
	"accountNumber": "058836722201",
	"sentBy": "Lucinda Pegasus User DCM",
	"emailSystemSentDate": 1469022825403,
	"emailManuallySentDate": null,
	"id": 119001,
	"deductionId": 1106905,
	"sendMail": null,
	"manuallyUpdated": false,
	"manuallyUpdatedOn": null,
	"persistedProposalId": "8EDB862C-2168-4A46-A978-FFACD04C3441",
	"type": "obligation",
	"consumerEmailAddress": null
}, {
	"payPlanProposalPersistId": 22481,
	"obligation": "Lewis Group",
	"accountNumber": "058836722202",
	"sentBy": "Lucinda Pegasus User DCM",
	"emailSystemSentDate": 1469022825407,
	"emailManuallySentDate": null,
	"id": 119007,
	"deductionId": 1123543,
	"sendMail": null,
	"manuallyUpdated": false,
	"manuallyUpdatedOn": null,
	"persistedProposalId": "8EDB862C-2168-4A46-A978-FFACD04C3441",
	"type": "obligation",
	"consumerEmailAddress": null
}, {
	"payPlanProposalPersistId": 22481,
	"obligation": "OK FURNITURE",
	"accountNumber": "400404725402",
	"sentBy": "Lucinda Pegasus User DCM",
	"emailSystemSentDate": 1469022825410,
	"emailManuallySentDate": null,
	"id": 119002,
	"deductionId": 1106907,
	"sendMail": null,
	"manuallyUpdated": false,
	"manuallyUpdatedOn": null,
	"persistedProposalId": "8EDB862C-2168-4A46-A978-FFACD04C3441",
	"type": "obligation",
	"consumerEmailAddress": null
}, {
	"payPlanProposalPersistId": 22481,
	"obligation": "Vehicle Finance - MFC (Motor Finance Corporation)",
	"accountNumber": "30407710012",
	"sentBy": "Lucinda Pegasus User DCM",
	"emailSystemSentDate": 1469022825410,
	"emailManuallySentDate": null,
	"id": 119004,
	"deductionId": 1106909,
	"sendMail": null,
	"manuallyUpdated": false,
	"manuallyUpdatedOn": null,
	"persistedProposalId": "8EDB862C-2168-4A46-A978-FFACD04C3441",
	"type": "obligation",
	"consumerEmailAddress": null
}, {
	"payPlanProposalPersistId": 22481,
	"obligation": null,
	"accountNumber": null,
	"sentBy": "Lucinda Pegasus User DCM",
	"emailSystemSentDate": 1469022823640,
	"emailManuallySentDate": null,
	"id": 4416,
	"deductionId": null,
	"sendMail": null,
	"manuallyUpdated": false,
	"manuallyUpdatedOn": null,
	"persistedProposalId": null,
	"type": "client",
	"consumerEmailAddress": "belindaduplessis975@gmail.com"
}]
*/
