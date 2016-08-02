/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coza.dcmgroup.proposal;

import coza.dcmgroup.entity.dto.ProposalHistoryDTO;
import java.util.List;

/**
 *
 * @author ninjav
 */
public class SendProposalResponse {

    List<ProposalHistoryDTO> result;

    public List<ProposalHistoryDTO> getResult() {
        return result;
    }

    public void setResult(List<ProposalHistoryDTO> result) {
        this.result = result;
    }

}
