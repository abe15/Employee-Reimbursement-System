package com.revature.project1;

import io.javalin.Javalin;
import com.revature.project1.models.*;
import com.revature.project1.dao.*;

public class App {

    private static String baseUrl = "/internal/ers";
    private static String registerUrl = baseUrl + "/user-management/register";
    private static String loginUrl = baseUrl + "/user-management/login";
    private static String userInfoUrl = baseUrl + "/user-management/users";
    private static String reimbUrl = baseUrl + "/reimbursement-management/reimbursement-ticket";
    // private String loginUrl = baseUrl + "/reimbursement-management/login";

    private static IDao<ReimbursementTicketModel> reimbDao = new ReimbursementDAO();
    private static IDao<UserModel> userDao = new UserDao();

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Javalin app = Javalin.create();
        app.get("/", ctx -> ctx.result("Hello World"));

        app.get(userInfoUrl, ctx -> ctx.result(userDao.getAll().toString()));

        app.post(registerUrl + "", ctx -> {
            userDao.save(new UserModel("Abraham", "BArboza", "abe", "abe@gmail.com", 1, 1));

        });

        app.start(7070);

    }
}
