package com.revature.project1.controllers;

import java.security.Key;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project1.dao.user.IUserDao;
import com.revature.project1.dao.user.impl.UserDaoSQL;
import com.revature.project1.models.ReimbursementTicketModel;
import com.revature.project1.models.UserModel;
import com.revature.project1.services.ITicketService;
import com.revature.project1.services.IUserService;
import com.revature.project1.services.TicketServiceImpl;
import com.revature.project1.services.UserServiceImpl;
import com.revature.project1.util.SecretKeyHolder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

public class TicketController {
    private static Logger logger = LoggerFactory.getLogger(TicketController.class);
    private static SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static ITicketService tServ = new TicketServiceImpl();

    private static String[] parseJwt(Context ctx) {
        String[] s = new String[3];

        String token = ctx.header("Authorization");
        Claims claims = (Claims) Jwts.parserBuilder().setSigningKey(SecretKeyHolder.key)
                .build()
                .parse(token).getBody();
        s[0] = claims.getSubject(); // username
        s[1] = claims.get("user-id").toString(); // user-id
        s[2] = claims.get("user-role").toString();// role
        return s;
    }

    // handle registration request
    public static Handler submitTicket = ctx -> {

        // username
        // ticket type
        // description
        // amount

        logger.info("User is submitting ticket");
        String[] jwtHeaders = parseJwt(ctx);
        ObjectMapper om = new ObjectMapper();
        String body = ctx.body();
        JsonNode jsonNode = om.readTree(body);

        ReimbursementTicketModel t = new ReimbursementTicketModel();
        t.setReimbAmount(Double.parseDouble(jsonNode.get("reimbAmount").asText()));
        t.setReimbSubmitted(LocalDate.now());
        t.setReimbDescription(jsonNode.get("reimbDescription").asText());
        t.setReimbStausId(1);
        t.setReimbTypeId(Integer.parseInt(jsonNode.get("reimbTypeId").asText()));
        t.setReimbAuthor(Integer.parseInt(jwtHeaders[1]));
        // 3. do service call
        boolean isCreated = tServ
                .submitReimbTicket(t);

        // 4. render the response
        if (isCreated == true) {
            logger.info("Successful ticket submission");
            ctx.html("New ticket submitted");
            ctx.status(HttpCode.CREATED);
        } else {
            ctx.html("Failed ticket submission");
            ctx.status(HttpCode.NO_CONTENT);
        }
    };

    // handle registration request
    public static Handler getTicketByUserName = ctx -> {
        String[] jwtHeaders = parseJwt(ctx);
        List<ReimbursementTicketModel> t = tServ.getAllReimTicketsByUserName(Integer.parseInt(jwtHeaders[1]));
        ctx.result(t.toString());
        ctx.status(HttpCode.CREATED);
    };

    // input required Approved or Denied , ticket id
    public static Handler updateTicketStatus = ctx -> {
        String[] jwtHeaders = parseJwt(ctx);
        ReimbursementTicketModel t = new ReimbursementTicketModel();
        t.setReimbResolver(Integer.parseInt(jwtHeaders[1]));
        List<ReimbursementTicketModel> t = tServ.updateReimbTicket();
        ctx.result(t.toString());
        ctx.status(HttpCode.CREATED);
    };
}
