package com.revature.project1.dao.reimbursementTicket;

import java.util.List;
import java.util.Optional;
import com.revature.project1.models.ReimbursementTicketModel;

public interface IReimbursementDao {

    public Optional<ReimbursementTicketModel> findTicketsById(long id);

    public List<ReimbursementTicketModel> getAll();

    public List<ReimbursementTicketModel> getTicketsByAuthorUserName(Integer username);

    public int save(ReimbursementTicketModel t);

    public void updateTicketStatusById(Integer id, Integer statusId, Integer resolver);

}
