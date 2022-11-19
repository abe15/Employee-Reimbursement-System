package com.revature.project1.dao;

import java.util.List;
import java.util.Optional;

import com.revature.project1.models.ReimbursementTicket;

public class ReimbursementDAO implements IDao<ReimbursementTicket> {

    @Override
    public Optional<ReimbursementTicket> get(long id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public List<ReimbursementTicket> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(ReimbursementTicket t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(ReimbursementTicket t, String[] params) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(ReimbursementTicket t) {
        // TODO Auto-generated method stub

    }

}
