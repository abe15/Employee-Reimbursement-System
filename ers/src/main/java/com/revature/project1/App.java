package com.revature.project1;

import io.javalin.*;
import io.javalin.http.Handler;
import io.jsonwebtoken.io.Decoders;
import com.revature.project1.controllers.UserController;
import com.revature.project1.controllers.TicketController;

public class App {

    private static String baseUrl = "/internal/ers";
    private static String registerUrl = baseUrl + "/users";
    private static String loginUrl = baseUrl + "/session";
    private static String userInfoUrl = baseUrl + "/users";
    private static String reimbUrl = baseUrl + "/reimb-tickets";
    private static String reimbUpdateUrl = baseUrl + "/reimb-tickets/update";

    // 1.
    // Algorithm algorithm = Algorithm.HMAC256("very_secret");

    public static void main(String[] args) {

        Javalin app = Javalin.create();
        app.get("/", ctx -> ctx.result("Hello World"));

        app.post(registerUrl, UserController.register);

        app.post(loginUrl, UserController.login);

        app.post(reimbUrl, TicketController.submitTicket);
        app.get(reimbUrl, TicketController.getTicketByUserName);
        app.post(reimbUpdateUrl, TicketController.submitTicket);
        app.start(7070);

    }
}
