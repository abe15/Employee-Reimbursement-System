package com.revature.project1;

import io.javalin.*;
import io.javalin.http.HttpCode;

import java.security.Key;
import java.util.Optional;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import com.revature.project1.controllers.UserController;
import com.revature.project1.dao.*;
import com.revature.project1.dao.reimbursementTicket.IReimbursementDao;
import com.revature.project1.dao.reimbursementTicket.impl.ReimbursementDaoList;
import com.revature.project1.dao.user.IUserDao;
import com.revature.project1.dao.user.impl.UserDaoList;
import com.revature.project1.dao.user.impl.UserDaoSQL;
import com.revature.project1.models.*;

public class App {

    private static String baseUrl = "/internal/ers";
    private static String registerUrl = baseUrl + "/users";
    private static String loginUrl = baseUrl + "/session";
    private static String userInfoUrl = baseUrl + "/users";
    private static String reimbUrl = baseUrl + "/reimbursement-tickets";

    private static IReimbursementDao reimbDao = new ReimbursementDaoList();
    private static IUserDao userDao = new UserDaoSQL();
    // 1.
    // Algorithm algorithm = Algorithm.HMAC256("very_secret");

    public static void main(String[] args) {

        Javalin app = Javalin.create();
        app.get("/", ctx -> ctx.result("Hello World"));

        app.get(userInfoUrl, ctx -> ctx.result(userDao.getAllUsers().toString()));

        app.post(registerUrl, UserController.register);

        app.post(loginUrl, UserController.login);

        app.start(7070);

    }
}
