package com.revature.project1.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.revature.project1.models.ReimbursementTicketModel;

public class ReimbursementDAO implements IDao<ReimbursementTicketModel> {

    private List<ReimbursementTicketModel> reimbTickets = new ArrayList<>();

    public ReimbursementDAO() {
        /** empty n */
    }

    @Override
    public Optional<ReimbursementTicketModel> get(long id) {
        return Optional.ofNullable(reimbTickets.get((int) id));
    }

    @Override
    public List<ReimbursementTicketModel> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(ReimbursementTicketModel t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(ReimbursementTicketModel t, String[] params) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(ReimbursementTicketModel t) {
        // TODO Auto-generated method stub

    }

}
