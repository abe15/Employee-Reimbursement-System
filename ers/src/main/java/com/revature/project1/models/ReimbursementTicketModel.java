package com.revature.project1.models;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ReimbursementTicketModel {
    private Integer reimbId;
    private Integer reimbTypeId;
    private double reimbAmount;
    private LocalDateTime reimbSubmitted;
    private LocalDateTime reimbResolved;
    private String reimbDescription;
    private String reimbReceipt;
    private Integer reimbStatusId;
    private Integer reimbAuthor;
    private Integer reimbResolver;
    private String reimbTypeName;

    public String getReimbTypeName() {
        return reimbTypeName;
    }

    public void setReimbTypeName(String reimbTypeName) {
        // --lodging 1, travel 2, food 3, other 4
        switch (reimbTypeName) {
            case "LODGING":
                reimbTypeId = 1;
                break;
            case "TRAVEL":
                reimbTypeId = 2;
                break;
            case "FOOD":
                reimbTypeId = 3;
                break;
            default:
                reimbTypeId = 4;
                break;

        }

        this.reimbTypeName = reimbTypeName;
    }

    public int getReimbId() {
        return reimbId;
    }

    public void setReimbId(int s) {
        reimbId = s;
    }

    public Integer getReimbTypeId() {
        return reimbTypeId;
    }

    public void setReimbTypeId(int a) {
        // --lodging 1, travel 2, food 3, other 4
        switch (a) {
            case 1:
                reimbTypeName = "LODGING";
                break;
            case 2:
                reimbTypeName = "TRAVEL";
                break;
            case 3:
                reimbTypeName = "FOOD";
                break;
            default:
                reimbTypeName = "OTHER";
                break;

        }

        this.reimbTypeId = a;
    }

    public double getReimbAmount() {
        return reimbAmount;
    }

    public void setReimbAmount(double reimbAmount) {
        this.reimbAmount = reimbAmount;
    }

    public LocalDateTime getReimbSubmitted() {
        return reimbSubmitted;
    }

    public void setReimbSubmitted(LocalDateTime reimbSubmitted) {
        this.reimbSubmitted = reimbSubmitted;
    }

    public LocalDateTime getReimbResolved() {
        return reimbResolved;
    }

    public void setReimbResolved(LocalDateTime reimbResolved) {
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

    public ReimbursementTicketModel(int reimbId, int reimbTypeId, double reimbAmount, LocalDateTime reimbSubmitted,
            LocalDateTime reimbResolved, String reimbDescription, String reimbReceipt, Integer reimbType,
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

    @Override
    public String toString() {

        ObjectMapper objectMapper = new ObjectMapper();

        JavaTimeModule module = new JavaTimeModule();
        objectMapper.registerModule(module);
        String json = "";
        try {
            json = objectMapper.writeValueAsString(this);
        } catch (Exception e) {
        }
        return json;
    }
}
