package coza.dcmgroup.entity.dto;

/**
 * Created by gert.erasmus on 2014/09/04.
 */
public class ProposalEmailObligationDTO {

    @Override
    public String toString() {
        return "ProposalEmailObligationDTO{" +
                "creditorSubject='" + creditorSubject + '\'' +
                ", creditorEmailBody=" + creditorEmailBody + '\'' +
                ", consumerSubject=" + consumerSubject + '\'' +
                ", consumerEmailBody=" + consumerEmailBody + '\'' +
                ", consumer='" + consumer + '\'' +
                ", identityNumber='" + identityNumber + '\'' +
                ", ncrRegistrationNumber='" + ncrRegistrationNumber + '\'' +
                ", organisation='" + organisation + '\'' +
                ", contactName='" + contactName+ '\'' +
                ", telephoneNumber='" + telephoneNumber+ '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", consumerEmail='" + consumerEmail + '\'' +
                ", payPlanProposalPersistObligationCommunicationId= " +  payPlanProposalPersistObligationCommunicationId +
                ", persistedProposalId= '" + '\'' +
                '}';
    }

    // variables used
    private String creditorSubject,
            creditorEmailBody,
            consumerSubject,
            consumerEmailBody,
            consumer,
            identityNumber,
            ncrRegistrationNumber,
            organisation,
            contactName,
            telephoneNumber,
            emailAddress,
            consumerEmail,
            persistedProposalId;
    Integer payPlanProposalPersistObligationCommunicationId;

    public String getPersistedProposalId () {return persistedProposalId;}
    public void setPersistedProposalId (String persistedProposalId) {this.persistedProposalId = persistedProposalId;}

    public String getCreditorSubject () {return creditorSubject;}
    public void setCreditorSubject (String creditorSubject) {this.creditorSubject = creditorSubject; }

    public String getcreditorEmailBody () {return creditorEmailBody;}
    public void setcreditorEmailBody (String creditorEmailBody) {this.creditorEmailBody = creditorEmailBody; }

    public String getConsumerSubject () { return consumerSubject;}
    public void setConsumerSubject (String consumerSubject) {this.consumerSubject = consumerSubject;}

    public String getConsumerEmailBody () {return consumerEmailBody;}
    public void setConsumerEmailBody (String consumerEmailBody) { this.consumerEmailBody = consumerEmailBody; }

    public String getConsumer() {return consumer;}
    public void setConsumer (String consumer) { this.consumer = consumer;}

    public String getIdentityNumber() {return identityNumber;}
    public void setIdentityNumber(String identityNumber) {this.identityNumber = identityNumber;}

    public String getNCRRegistrationNumber() {return ncrRegistrationNumber;}
    public void setNCRRegistrationNumber (String ncrRegistrationNumber) {this.ncrRegistrationNumber = ncrRegistrationNumber;}

    public String getOrganisation() {return organisation;}
    public void setOrganisation(String organisation) {this.organisation = organisation;}

    public String getContactName() {return contactName;}
    public void setContactName(String contactName) {this.contactName = contactName;}

    public String getTelephoneNumber() {return telephoneNumber;}
    public void setTelephoneNumber(String telephoneNumber) {this.telephoneNumber = telephoneNumber;}

    public String getEmailAddress() {return emailAddress;}
    public void setEmailAddress(String emailAddress) {this.emailAddress = emailAddress;}

    public String getConsumerEmail() {return consumerEmail;}
    public void setConsumerEmail(String consumerEmail) {this.consumerEmail = consumerEmail;}

    public Integer getPayPlanProposalPersistId () { return payPlanProposalPersistObligationCommunicationId; }
    public void getPayPlanProposalPersistId (Integer payPlanProposalPersistObligationCommunicationId) { this.payPlanProposalPersistObligationCommunicationId = payPlanProposalPersistObligationCommunicationId; }

}

