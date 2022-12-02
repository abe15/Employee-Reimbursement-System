package com.revature.project1.services;

import java.util.List;

import com.revature.project1.models.ReimbursementTicketModel;

public interface ITicketService {

    public boolean submitReimbTicket(ReimbursementTicketModel ticket);

    public List<ReimbursementTicketModel> getAllReimTickets();

    public List<ReimbursementTicketModel> getAllReimTicketsByUserName(Integer username);

    public int updateReimbTicket(ReimbursementTicketModel ticket);

}
