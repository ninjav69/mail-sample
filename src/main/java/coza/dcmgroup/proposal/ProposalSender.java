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
public interface ProposalSender {
    public SendProposalResponse sendProposal(SendProposalRequest request);
}
