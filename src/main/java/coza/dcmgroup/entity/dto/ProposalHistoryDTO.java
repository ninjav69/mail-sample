package coza.dcmgroup.entity.dto;

import java.util.Date;

/**
 * Created by gert.erasmus on 2014/08/21.
 */
public class ProposalHistoryDTO {

    private Integer payPlanProposalPersistId;
    private String obligation;
    private String accountNumber;
    private String sentBy;
    private Date emailSystemSentDate;
    private Date emailManuallySentDate;
    private Integer id;
    private Integer deductionId;
    private Boolean sendMail;
    private Boolean manuallyUpdated;
    private Date manuallyUpdatedOn;
    private String persistedProposalId;
    private String type;
    private String consumerEmailAddress;

    public String getConsumerEmailAddress() {return consumerEmailAddress;}
    public void setConsumerEmailAddress (String consumerEmailAddress) { this.consumerEmailAddress = consumerEmailAddress; }

    public String getType() {return type;}
    public void setType (String type) { this.type = type; }

    public String getPersistedProposalId () { return persistedProposalId; }
    public void setPersistedProposalId (String persistedProposalId) { this.persistedProposalId = persistedProposalId; }

    public Integer getDeductionId () { return deductionId;}
    public void setDeductionId (Integer deductionId) { this.deductionId = deductionId;}

    public Integer getPayPlanProposalPersistId () { return payPlanProposalPersistId; }

    public void setPayPlanProposalPersistId(Integer payPlanProposalPersistId) {this.payPlanProposalPersistId = payPlanProposalPersistId;}

    public String getObligation() {
        return obligation;
    }

    public void setObligation(String obligation) {
        this.obligation = obligation;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {this.accountNumber = accountNumber;}

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {this.sentBy = sentBy;}

    public Date getEmailSystemSentDate() {
        return emailSystemSentDate;
    }

    public void setEmailSystemSentDate(Date emailSystemSentDate) {this.emailSystemSentDate = emailSystemSentDate;}

    public Date getEmailManuallySentDate() {
        return emailManuallySentDate;
    }

    public void setEmailManuallySentDate(Date emailManuallySentDate) {this.emailManuallySentDate = emailManuallySentDate;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getSendMail() {
        return sendMail;
    }

    public void setSendMail(Boolean sendMail) {
        this.sendMail = sendMail;
    }

    public Boolean getManuallyUpdated() {
        return manuallyUpdated;
    }

    public void setManuallyUpdated(Boolean manuallyUpdated) {
        this.manuallyUpdated = manuallyUpdated;
    }

    public Date getManuallyUpdatedOn() {
        return manuallyUpdatedOn;
    }

    public void setManuallyUpdatedOn(Date manuallyUpdatedOn) {
        this.manuallyUpdatedOn = manuallyUpdatedOn;
    }

    @Override
    public String toString() {
        return "ProposalHistoryDTO{" +
                "PayPlanProposalPersistId=" + payPlanProposalPersistId +
                ", Obligation='" + obligation + '\'' +
                ", AccountNumber='" + accountNumber + '\'' +
                ", SentBy='" + sentBy + '\'' +
                ", EmailSystemSentDate= '" + emailSystemSentDate + '\'' +
                ", EmailManuallySentDate= '" + emailManuallySentDate + '\'' +
                ", Id= " + id +
                ", Type=" + type +
                ", ManuallyUpdated = '" + manuallyUpdated + '\'' +
                ", ConsumerEmailAddress = '" + consumerEmailAddress + '\'' +
                '}';
    }
}
