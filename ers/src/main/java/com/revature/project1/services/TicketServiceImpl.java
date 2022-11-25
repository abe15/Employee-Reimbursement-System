package com.revature.project1.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.project1.dao.reimbursementTicket.IReimbursementDao;
import com.revature.project1.dao.reimbursementTicket.impl.ReimbursementDaoList;
import com.revature.project1.dao.reimbursementTicket.impl.ReimbursementDaoSql;
import com.revature.project1.models.ReimbursementTicketModel;

public class TicketServiceImpl implements ITicketService {

    private static Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

    private static IReimbursementDao ticketDao;

    public TicketServiceImpl() {
        ticketDao = new ReimbursementDaoSql();
    }

    public TicketServiceImpl(IReimbursementDao ud) {
        this.ticketDao = ud;
    }

    @Override
    public boolean submitReimbTicket(ReimbursementTicketModel ticket) {
        // TODO Auto-generated method stub
        return (ticketDao.save(ticket)) == 1;
    }

    @Override
    public List<ReimbursementTicketModel> getAllReimTickets() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ReimbursementTicketModel> getAllReimTicketsByUserName(Integer username) {
        // TODO Auto-generated method stub
        return ticketDao.getTicketsByAuthorUserName(username);
    }

    @Override
    public boolean updateReimbTicket(ReimbursementTicketModel ticket) {
        ticketDao.updateTicketStatusById(ticket.getReimbId(), ticket.getReimbStausId(), ticket.getReimbAuthor());
        return true;
    }

}
