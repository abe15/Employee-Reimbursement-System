package com.revature.project1;

import io.javalin.Javalin;

public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Javalin app = Javalin.create();
        app.get("/", ctx -> ctx.result("Hello World"))
                .start(7070);

    }
}
