package com.revature.project1.models;

import java.sql.Date;
import java.util.Optional;

public class ReimbursementTicketModel {
    private final int reimbId;
    private final int reimbTypeId;
    private double reimbAmount;
    private Date reimbSubmitted;
    private Optional<Date> reimbResolved;
    private String reimbDescription;
    private String reimbReceipt;
    private String reimbType;
    private String reimbAuthor;
    private String reimbResolver;

    public int getReimbId() {
        return reimbId;
    }

    public int getReimbTypeId() {
        return reimbTypeId;
    }

    public double getReimbAmount() {
        return reimbAmount;
    }

    public void setReimbAmount(double reimbAmount) {
        this.reimbAmount = reimbAmount;
    }

    public Date getReimbSubmitted() {
        return reimbSubmitted;
    }

    public void setReimbSubmitted(Date reimbSubmitted) {
        this.reimbSubmitted = reimbSubmitted;
    }

    public Optional<Date> getReimbResolved() {
        return reimbResolved;
    }

    public void setReimbResolved(Optional<Date> reimbResolved) {
        this.reimbResolved = reimbResolved;
    }

    public String getReimbDescription() {
        return reimbDescription;
    }

    public void setReimbDescription(String reimbDescription) {
        this.reimbDescription = reimbDescription;
    }

    public String getReimbReceipt() {
        return reimbReceipt;
    }

    public void setReimbReceipt(String reimbReceipt) {
        this.reimbReceipt = reimbReceipt;
    }

    public String getReimbType() {
        return reimbType;
    }

    public void setReimbType(String reimbType) {
        this.reimbType = reimbType;
    }

    public String getReimbAuthor() {
        return reimbAuthor;
    }

    public void setReimbAuthor(String reimbAuthor) {
        this.reimbAuthor = reimbAuthor;
    }

    public String getReimbResolver() {
        return reimbResolver;
    }

    public void setReimbResolver(String reimbResolver) {
        this.reimbResolver = reimbResolver;
    }

    public ReimbursementTicketModel(int reimbId, int reimbTypeId, double reimbAmount, Date reimbSubmitted,
            Optional<Date> reimbResolved, String reimbDescription, String reimbReceipt, String reimbType,
            String reimbAuthor, String reimbResolver) {
        this.reimbId = reimbId;
        this.reimbTypeId = reimbTypeId;
        this.reimbAmount = reimbAmount;
        this.reimbSubmitted = reimbSubmitted;
        this.reimbResolved = reimbResolved;
        this.reimbDescription = reimbDescription;
        this.reimbReceipt = reimbReceipt;
        this.reimbType = reimbType;
        this.reimbAuthor = reimbAuthor;
        this.reimbResolver = reimbResolver;
    }

}
