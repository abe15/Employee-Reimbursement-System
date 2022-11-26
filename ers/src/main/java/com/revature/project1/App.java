package com.revature.project1;

import io.javalin.*;
import io.javalin.core.security.RouteRole;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import com.revature.project1.controllers.UserController;
import com.revature.project1.util.SecretKeyHolder;
import com.revature.project1.controllers.TicketController;

enum Role implements RouteRole {
    ANYONE, EMPLOYEE, MANAGER, ADMIN;

}

public class App {

    private static String baseUrl = "/internal/ers";
    private static String registerUrl = baseUrl + "/users";
    private static String loginUrl = baseUrl + "/session";
    private static String userInfoUrl = baseUrl + "/users";
    private static String reimbUrl = baseUrl + "/reimb-tickets";
    private static String reimbUpdateUrl = baseUrl + "/reimb-tickets/update";

    static Role getUserRole(Context ctx) {
        String token = "";
        token = ctx.header("Authorization");
        if (token == null) {
            return Role.ANYONE;
        }
        Claims claims = (Claims) Jwts.parserBuilder().setSigningKey(SecretKeyHolder.key)
                .build()
                .parse(token).getBody();

        Integer roleId = Integer.parseInt(claims.get("user-role").toString());

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

    public static void main(String[] args) {

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

        app.get("/", ctx -> ctx.result("Hello World"), Role.ANYONE);

        app.post(registerUrl, UserController.register, Role.ANYONE);
        app.post(loginUrl, UserController.login, Role.ANYONE);

        app.post(reimbUrl, TicketController.submitTicket, Role.EMPLOYEE, Role.ADMIN, Role.MANAGER);

        app.get(reimbUrl, TicketController.getTicketByUserName, Role.EMPLOYEE, Role.ADMIN, Role.MANAGER);

        app.post(reimbUpdateUrl, TicketController.updateTicketStatus, Role.ADMIN, Role.MANAGER);

        app.start(7070);

    }
}
