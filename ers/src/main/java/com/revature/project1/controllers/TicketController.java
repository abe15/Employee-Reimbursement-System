package com.revature.project1.controllers;

import java.time.LocalDateTime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.project1.models.ReimbursementTicketModel;
import com.revature.project1.models.TicketStatus;
import com.revature.project1.services.ITicketService;

import io.javalin.Javalin;

import io.javalin.http.Handler;
import io.javalin.http.HttpCode;

import com.revature.project1.models.*;

public class TicketController {

    private static Logger logger = LoggerFactory.getLogger(TicketController.class);

    private static ITicketService tServ;

    // api endpoints
    String reimbUrl = "/reimb-tickets";
    String reimbUrlAll = "/reimb-tickets/all";

    public TicketController(ITicketService tServ, Javalin app) {
        this.tServ = tServ;

        // Ticket submission
        app.post(reimbUrl, TicketController.submitTicket, Role.EMPLOYEE, Role.ADMIN, Role.MANAGER);

        // update ticket status
        app.patch(reimbUrl, TicketController.updateTicketStatus, Role.ADMIN, Role.MANAGER);

        // Employee ticket search
        app.get(reimbUrl, TicketController.getTicketsEmployee, Role.EMPLOYEE, Role.ADMIN, Role.MANAGER);

        // Manager elevated ticket search
        app.get(reimbUrlAll, TicketController.getAllReimbTickets, Role.ADMIN, Role.MANAGER);
    }

    // handle ticket submission
    public static Handler submitTicket = ctx -> {

        logger.info("User is submitting ticket");

        ObjectMapper om = new ObjectMapper();
        String body = ctx.body();

        // attempt to parse user input
        try {
            JsonNode jsonNode = om.readTree(body);

            ReimbursementTicketModel t = new ReimbursementTicketModel();
            t.setReimbAmount(Double.parseDouble(jsonNode.get("reimbAmount").asText()));
            t.setReimbSubmitted(LocalDateTime.now());
            t.setReimbDescription(jsonNode.get("reimbDescription").asText());
            t.setReimbStatus(TicketStatus.PENDING);
            t.setReimbTypeId(Integer.parseInt(jsonNode.get("reimbTypeId").asText()));
            t.setReimbAuthor(Integer.parseInt(ctx.attribute("user-id")));

            // do service call
            boolean isCreated = tServ.submitReimbTicket(t);

            // render the response
            if (isCreated) {
                logger.info("Successful ticket submission");
                ctx.html("Successful ticket submission");
                ctx.status(HttpCode.CREATED);
            } else {
                ctx.html("Failed ticket submission");
                ctx.status(HttpCode.BAD_REQUEST);
            }

        } catch (Exception e) {
            logger.info("Invalid input");
            ctx.result("Invalid input");
            ctx.status(HttpCode.BAD_REQUEST);
        }

    };

    // Employee ticket retrieval with filter by status id and type id
    public static Handler getTicketsEmployee = ctx -> {

        List<ReimbursementTicketModel> tickets = tServ
                .getAllReimTicketsByUserName(Integer.parseInt(ctx.attribute("user-id")));

        Map<String, String> m = new HashMap<>();
        if (ctx.queryParam("reimbStatusId") != null && !ctx.queryParam("reimbStatusId").isEmpty()) {
            m.put("reimb_status_id", ctx.queryParam("reimbStatusId"));
        }
        if (ctx.queryParam("reimbTypeId") != null && !ctx.queryParam("reimbTypeId").isEmpty()) {
            m.put("reimb_type_id", ctx.queryParam("reimbTypeId"));
        }

        if (m.get("reimb_status_id") != null) {
            tickets = tickets.stream()
                    .filter(t -> t.getReimbStatusId().toString().equals(m.get("reimb_status_id")))
                    .collect(Collectors.toList());

        }
        if (m.get("reimb_type_id") != null) {
            tickets = tickets.stream()
                    .filter(t -> t.getReimbTypeId().toString().equals(m.get("reimb_type_id")))
                    .collect(Collectors.toList());

        }

        ctx.json(tickets.toString());
        ctx.status(HttpCode.CREATED);
    };

    // Manager ticket retrieval with filter by status id and reimb author
    public static Handler getAllReimbTickets = ctx -> {

        Map<String, String> m = new HashMap<>();
        if (ctx.queryParam("reimbStatusId") != null && !ctx.queryParam("reimbStatusId").isEmpty()) {
            m.put("reimb_status_id", ctx.queryParam("reimbStatusId"));
        }
        if (ctx.queryParam("reimbAuthor") != null && !ctx.queryParam("reimbAuthor").isEmpty()) {
            m.put("reimb_author", ctx.queryParam("reimbAuthor"));
        }

        List<ReimbursementTicketModel> tickets = tServ.getAllReimTickets();

        if (m.get("reimb_status_id") != null) {
            tickets = tickets.stream()
                    .filter(t -> t.getReimbStatusId().toString().equals(m.get("reimb_status_id")))
                    .collect(Collectors.toList());

        }
        if (m.get("reimb_author") != null) {
            tickets = tickets.stream()
                    .filter(t -> t.getReimbAuthor().toString().equals(m.get("reimb_author")))
                    .collect(Collectors.toList());

        }

        ctx.json(tickets.toString());
        ctx.status(HttpCode.OK);
    };

    // Manager update to Approved or Denied
    public static Handler updateTicketStatus = ctx -> {

        ObjectMapper om = new ObjectMapper();
        String body = ctx.body();

        try {
            JsonNode jsonNode = om.readTree(body);

            ReimbursementTicketModel t = new ReimbursementTicketModel();

            t.setReimbId(Integer.parseInt(jsonNode.get("reimbId").asText()));
            t.setReimbResolver(Integer.parseInt(ctx.attribute("user-id")));

            t.setReimbStatusName(jsonNode.get("reimbStatus").asText());

            // 3. do service call
            Integer isUpdated = tServ.updateReimbTicket(t);

            // 4. render the response
            if (isUpdated >= 1) {
                logger.info("Ticket succesfully updated");
                ctx.result("Ticket succesfully updated");
                ctx.status(HttpCode.ACCEPTED);
            } else if (isUpdated < 0) {
                logger.info("Ticket not updated");
                ctx.result("Can only update from Pending to Approved or Denined");
                ctx.status(HttpCode.BAD_REQUEST);
            } else {
                ctx.html("Failed to update ticket");
                ctx.status(HttpCode.BAD_REQUEST);
            }

        } catch (Exception e) {
            logger.info("Invalid input");
            ctx.result("Invalid input");
            ctx.status(HttpCode.BAD_REQUEST);
        }

    };
}
