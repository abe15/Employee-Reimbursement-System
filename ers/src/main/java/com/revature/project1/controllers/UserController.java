package com.revature.project1.controllers;

import java.security.Key;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project1.dao.user.IUserDao;
import com.revature.project1.dao.user.impl.UserDaoSQL;
import com.revature.project1.models.UserModel;
import com.revature.project1.services.IUserService;
import com.revature.project1.services.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.javalin.http.Handler;
import io.javalin.http.HttpCode;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    private static SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static IUserService uServ = new UserServiceImpl();

    // handle registration request
    public static Handler register = ctx -> {

        logger.info("User is making a registration request...");

        String body = ctx.body();

        // convert the body into a User object
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        UserModel target = om.readValue(body, UserModel.class);
        logger.info("New User: " + target);

        // 3. do service call
        boolean isCreated = uServ.registerUser(target);

        // 4. render the response
        if (isCreated == true) {
            ctx.html("The new user has been created successfully.");
            ctx.status(HttpCode.CREATED);
        } else {
            ctx.html("Error during creation. Try again.");
            ctx.status(HttpCode.NO_CONTENT);
        }
    };

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

        public UsernamePasswordCombo() {
        }
    }

    public static Handler login = ctx -> {

        logger.info("User attempting to login...");
        String body = ctx.body();
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        UsernamePasswordCombo target = om.readValue(body, UsernamePasswordCombo.class);
        boolean loginSuccessful = uServ.login(target.getUsername(), target.getPassword());

        // 4. render the response
        if (loginSuccessful == true) {
            ctx.html("User log in successful");
            String jws = Jwts.builder().setSubject(target.getUsername()).signWith(key).compact();
            ctx.result(jws);
            ctx.status(HttpCode.ACCEPTED);
        } else {
            ctx.html("User log in failed");
            ctx.status(HttpCode.UNAUTHORIZED);
        }
    };
}