package com.revature.project1.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.revature.project1.models.ReimbursementTicketModel;

public interface ITicketService {

    public boolean submitReimbTicket(ReimbursementTicketModel ticket);

    public List<ReimbursementTicketModel> getAllReimTickets(Map<String, String> m);

    public List<ReimbursementTicketModel> getAllReimTicketsByUserName(Integer username);

    public int updateReimbTicket(ReimbursementTicketModel ticket);

}
