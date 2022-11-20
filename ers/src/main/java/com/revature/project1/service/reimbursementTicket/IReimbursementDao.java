package com.revature.project1.service.reimbursementTicket;

import java.util.List;
import java.util.Optional;
import com.revature.project1.models.ReimbursementTicketModel;

public interface IReimbursementDao {

    public Optional<ReimbursementTicketModel> findTicketsById(long id);

    public List<ReimbursementTicketModel> getAll();

    public List<ReimbursementTicketModel> getTicketsByUserName(String username);

    public void save(ReimbursementTicketModel t);

    public void updateTicketStatusById(long t, String[] params);

}
