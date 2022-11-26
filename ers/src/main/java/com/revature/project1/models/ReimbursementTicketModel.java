package com.revature.project1.models;

import java.time.LocalDate;
import java.util.Optional;

public class ReimbursementTicketModel {
    private Integer reimbId;
    private Integer reimbTypeId;
    private double reimbAmount;
    private LocalDate reimbSubmitted;
    private Optional<LocalDate> reimbResolved;
    private String reimbDescription;
    private String reimbReceipt;
    private Integer reimbStatusId;
    private Integer reimbAuthor;
    private Integer reimbResolver;

    public int getReimbId() {
        return reimbId;
    }

    public int getReimbTypeId() {
        return reimbTypeId;
    }

    public void setReimbTypeId(int a) {
        this.reimbTypeId = a;
    }

    public double getReimbAmount() {
        return reimbAmount;
    }

    public void setReimbAmount(double reimbAmount) {
        this.reimbAmount = reimbAmount;
    }

    public LocalDate getReimbSubmitted() {
        return reimbSubmitted;
    }

    public void setReimbSubmitted(LocalDate reimbSubmitted) {
        this.reimbSubmitted = reimbSubmitted;
    }

    public Optional<LocalDate> getReimbResolved() {
        return reimbResolved;
    }

    public void setReimbResolved(Optional<LocalDate> reimbResolved) {
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

    public Integer getReimbStausId() {
        return reimbStatusId;
    }

    public void setReimbStausId(Integer reimbStatusType) {
        this.reimbStatusId = reimbStatusType;
    }

    public Integer getReimbAuthor() {
        return reimbAuthor;
    }

    public void setReimbAuthor(Integer reimbAuthor) {
        this.reimbAuthor = reimbAuthor;
    }

    public Integer getReimbResolver() {
        return reimbResolver;
    }

    public void setReimbResolver(Integer reimbResolver) {
        this.reimbResolver = reimbResolver;
    }

    public ReimbursementTicketModel() {
    }

    public ReimbursementTicketModel(int reimbId, int reimbTypeId, double reimbAmount, LocalDate reimbSubmitted,
            Optional<LocalDate> reimbResolved, String reimbDescription, String reimbReceipt, Integer reimbType,
            Integer reimbAuthor, Integer reimbResolver) {
        this.reimbId = reimbId;
        this.reimbTypeId = reimbTypeId;
        this.reimbAmount = reimbAmount;
        this.reimbSubmitted = reimbSubmitted;
        this.reimbResolved = reimbResolved;
        this.reimbDescription = reimbDescription;
        this.reimbReceipt = reimbReceipt;
        this.reimbStatusId = reimbType;
        this.reimbAuthor = reimbAuthor;
        this.reimbResolver = reimbResolver;
    }

}
