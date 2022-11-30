package com.revature.project1.services;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.project1.dao.reimbursementTicket.IReimbursementDao;

import com.revature.project1.dao.reimbursementTicket.impl.ReimbursementDaoSql;
import com.revature.project1.models.ReimbursementTicketModel;

public class TicketServiceImpl implements ITicketService {

    private static Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

    private static IReimbursementDao ticketDao;

    public TicketServiceImpl() {
        ticketDao = new ReimbursementDaoSql();
    }

    public TicketServiceImpl(IReimbursementDao ud) {
        ticketDao = ud;
    }

    @Override
    public boolean submitReimbTicket(ReimbursementTicketModel ticket) {

        return (ticketDao.save(ticket)) >= 1;
    }

    @Override
    public List<ReimbursementTicketModel> getAllReimTickets() {
        return ticketDao.getAll();
    }

    @Override
    public List<ReimbursementTicketModel> getAllReimTicketsByUserName(Integer username) {

        return ticketDao.getTicketsByAuthorUserName(username);
    }

    @Override
    public int updateReimbTicket(ReimbursementTicketModel ticket) {
        return ticketDao.updateTicketStatusById(ticket.getReimbId(), ticket.getReimbStatusId(),
                ticket.getReimbResolver());

    }

}
