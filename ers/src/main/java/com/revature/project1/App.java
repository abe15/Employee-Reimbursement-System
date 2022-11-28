package com.revature.project1;

import io.javalin.*;
import io.javalin.core.security.RouteRole;
import io.javalin.http.Context;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import com.revature.project1.controllers.UserController;
import com.revature.project1.util.SecretKeyHolder;
import com.revature.project1.controllers.TicketController;

enum Role implements RouteRole {
    ANYONE, EMPLOYEE, MANAGER, ADMIN;

}

public class App {
    // api endpoints
    private static String baseUrl = "";
    private static String registerUrl = baseUrl + "/users";
    private static String loginUrl = baseUrl + "/session";
    private static String reimbUrl = baseUrl + "/reimb-tickets";
    private static String reimbUrlAll = baseUrl + "/reimb-tickets/all";

    public static void main(String[] args) {

        // get user role from jwt, if not present role is ANYONE
        Javalin app = Javalin.create(config -> {
            config.accessManager((handler, ctx, routeRoles) -> {
                Role userRole = getUserRole(ctx);
                if (routeRoles.contains(userRole)) {
                    handler.handle(ctx);
                    return;
                } else {
                    ctx.status(401).result("Unauthorized");
                }
            });
        });

        // Registration and authentication
        app.post(registerUrl, UserController.register, Role.ANYONE);
        app.post(loginUrl, UserController.login, Role.ANYONE);

        // Ticket submission
        app.post(reimbUrl, TicketController.submitTicket, Role.EMPLOYEE, Role.ADMIN, Role.MANAGER);

        // update ticket status
        app.patch(reimbUrl, TicketController.updateTicketStatus, Role.ADMIN, Role.MANAGER);

        // Employee ticket search
        app.get(reimbUrl, TicketController.getTicketsEmployee, Role.EMPLOYEE, Role.ADMIN, Role.MANAGER);

        // Manager elevated ticket search
        app.get(reimbUrlAll, TicketController.getAllReimbTickets, Role.ADMIN, Role.MANAGER);

        app.start(7070);

    }

    // Gets user role from jwt. If missing, role=ANYONE.
    // if jwt is valid, adds user-id,user-role to context
    // for later use in handlers
    static Role getUserRole(Context ctx) {
        String token = "";
        token = ctx.header("Authorization");
        if (token == null) {
            return Role.ANYONE;
        }
        Integer roleId = 0;

        try {
            Claims claims = (Claims) Jwts.parserBuilder().setSigningKey(SecretKeyHolder.key)
                    .build()
                    .parse(token).getBody();

            // getting token fields
            roleId = Integer.parseInt(claims.get("user-role").toString());
            ctx.attribute("user-role", claims.get("user-role").toString());
            ctx.attribute("user-id", claims.get("user-id").toString());
            ctx.attribute("username", claims.getSubject());

        } catch (Exception e) {
            System.out.println("Token missing or failed validation");
        }
        switch (roleId) {
            case 1:
                return Role.EMPLOYEE;
            case 2:
                return Role.MANAGER;
            case 3:
                return Role.ADMIN;
            default:
                return Role.ANYONE;
        }

    }
}
