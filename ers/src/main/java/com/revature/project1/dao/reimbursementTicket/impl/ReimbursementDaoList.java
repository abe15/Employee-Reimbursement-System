package com.revature.project1.dao.reimbursementTicket.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.project1.dao.reimbursementTicket.IReimbursementDao;
import com.revature.project1.models.ReimbursementTicketModel;

public class ReimbursementDaoList implements IReimbursementDao {

    private List<ReimbursementTicketModel> tickets = new ArrayList<>();

    @Override
    public Optional<ReimbursementTicketModel> findTicketsById(long id) {
        for (ReimbursementTicketModel ticket : tickets) {
            if (ticket.getReimbId() == id) {
                return Optional.of(ticket);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<ReimbursementTicketModel> getAll() {
        // TODO Auto-generated method stub
        return tickets;
    }

    @Override
    public List<ReimbursementTicketModel> getTicketsByAuthorUserName(Integer username) {
        List<ReimbursementTicketModel> userTickets = new ArrayList<>();
        for (ReimbursementTicketModel ticket : tickets) {
            if (ticket.getReimbAuthor().equals(username)) {
                userTickets.add(ticket);
            }
        }

        return userTickets;
    }

    @Override
    public int save(ReimbursementTicketModel t) {
        tickets.add(t);
        return 1;
    }

    @Override
    public void updateTicketStatusById(Integer id, Integer statusId, Integer resolver) {

        for (ReimbursementTicketModel ticket : tickets) {
            if (ticket.getReimbId() == id) {
                ticket.setReimbResolved(null);
            }
        }

    }

}
