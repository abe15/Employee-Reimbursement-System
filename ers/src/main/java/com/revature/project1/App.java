package com.revature.project1;

import io.javalin.Javalin;

import java.util.Optional;

import com.revature.project1.models.*;
import com.revature.project1.service.*;
import com.revature.project1.service.reimbursementTicket.IReimbursementDao;
import com.revature.project1.service.reimbursementTicket.impl.ReimbursementDaoList;
import com.revature.project1.service.user.IUserDao;
import com.revature.project1.service.user.impl.UserDaoList;

public class App {

    private static String baseUrl = "/internal/ers";
    private static String registerUrl = baseUrl + "/users";
    private static String loginUrl = baseUrl + "/session";
    private static String userInfoUrl = baseUrl + "/users";
    private static String reimbUrl = baseUrl + "/reimbursement-tickets";

    private static IReimbursementDao reimbDao = new ReimbursementDaoList();
    private static IUserDao userDao = new UserDaoList();

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Javalin app = Javalin.create();
        app.get("/", ctx -> ctx.result("Hello World"));

        app.get(userInfoUrl, ctx -> ctx.result(userDao.getAllUsers().toString()));

        app.post(registerUrl + "", ctx -> {

            userDao.save(new UserModel(ctx.formParam("firstname"), ctx.formParam("lastname"), ctx.formParam("username"),
                    ctx.formParam("email"), ctx.formParam("password")));

        });

        app.post(loginUrl + "", ctx -> {
            Optional<UserModel> user = userDao.findByUserName(ctx.formParam("username"));
            if (user.isPresent()) {
                if (user.get().getPassword().equals(ctx.formParam("password"))) {

                    ctx.result("log in successful");
                    return;
                }

            }
            ctx.result("username or password incorrect");
            return;
        });

        app.start(7070);

    }
}
