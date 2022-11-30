package com.revature.project1.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.project1.models.UserModel;
import com.revature.project1.services.IUserService;
import com.revature.project1.services.UserServiceImpl;
import com.revature.project1.util.SecretKeyHolder;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.javalin.http.Handler;
import io.javalin.http.HttpCode;

import io.jsonwebtoken.*;

public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    private static IUserService uServ = new UserServiceImpl();

    // handle registration request
    public static Handler register = ctx -> {

        logger.info("User is making a registration request...");

        String body = ctx.body();
        ctx.bodyValidator(UserModel.class);
        // convert the body into a User object
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());

        // try deserializing json with registration info
        try {
            UserModel target = om.readValue(body, UserModel.class);
            logger.info("New User: %s{}", target);

            // attempt to register user
            boolean isCreated = uServ.registerUser(target);

            // check whether registration was successful
            if (isCreated) {
                ctx.result("The new user has been created successfully.");
                ctx.status(HttpCode.CREATED);
            } else {
                ctx.result("Error during creation. Try again.");
                ctx.status(HttpCode.CONFLICT);

            }

        } catch (Exception e) {
            // deserializing json with registration info failed.
            // incorrect property names or extra/missing input
            logger.warn("Invalid data : %s{}", e.getMessage());
            ctx.result("Validation failed");
            ctx.status(HttpCode.BAD_REQUEST);
        }

    };

    // Class used to deserialize username password for log in
    static class UsernamePasswordCombo {
        private String username;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        private String password;

    }

    public static Handler login = ctx -> {

        logger.info("Login handler called");
        String body = ctx.body();
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        try {
            logger.info("Attempting to parse login and password");
            UsernamePasswordCombo target = om.readValue(body, UsernamePasswordCombo.class);
            logger.info("Successful parsing");
            Optional<UserModel> user = uServ.login(target.getUsername(), target.getPassword());

            // 4. render the response
            if (user.isPresent()) {

                ctx.html("User log in successful");
                String jws = Jwts.builder().setSubject(target.getUsername()).claim("user-id", user.get().getUserId())
                        .claim("user-role", user.get().getUserRoleId())
                        .signWith(SecretKeyHolder.key).compact();

                ctx.result(jws);
                ctx.cookie("username", user.get().getUsername());
                ctx.status(HttpCode.ACCEPTED);
            } else {

                ctx.html("Invalid username or password");
                ctx.status(HttpCode.UNAUTHORIZED);
            }

        } catch (Exception e) {
            logger.info("Invalid input");
            ctx.result("Invalid input");
            ctx.status(HttpCode.BAD_REQUEST);
        }

    };

    private UserController() {
    }
}