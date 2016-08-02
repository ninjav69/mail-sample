/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coza.dcmgroup.proposal;

import coza.dcmgroup.entity.dto.ProposalHistoryDTO;
import coza.dcmgroup.entity.legacy.LegacyUser;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ninjav
 */
public class SendProposalRequest {

    private LegacyUser user;
    private int clientId;
    private int payPlanProposalPersistId;
    private String proposalCatchEmailAddress;
    private List<ProposalHistoryDTO> proposalHistory = new ArrayList<>();

    public LegacyUser getUser() {
        return user;
    }

    public void setUser(LegacyUser user) {
        this.user = user;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getPayPlanProposalPersistId() {
        return payPlanProposalPersistId;
    }

    public void setPayPlanProposalPersistId(int payPlanProposalPersistId) {
        this.payPlanProposalPersistId = payPlanProposalPersistId;
    }

    public String getProposalCatchEmailAddress() {
        return proposalCatchEmailAddress;
    }

    public void setProposalCatchEmailAddress(String proposalCatchEmailAddress) {
        this.proposalCatchEmailAddress = proposalCatchEmailAddress;
    }

    public List<ProposalHistoryDTO> getProposalHistory() {
        return proposalHistory;
    }

    public void setProposalHistory(List<ProposalHistoryDTO> proposalHistory) {
        this.proposalHistory = proposalHistory;
    }
}
