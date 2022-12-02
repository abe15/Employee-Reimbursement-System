package com.revature.project1.services;

import java.util.Set;

import com.revature.project1.models.Role;
import com.revature.project1.util.SecretKeyHolder;

import io.javalin.core.security.AccessManager;
import io.javalin.core.security.RouteRole;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

// Gets user role from jwt. If missing, role=ANYONE.
// Once roll is determined, execute handler if authorized to.
// if jwt is valid, adds user-id,user-role to context
// for later use in handlers
public class AuthService implements AccessManager {

    @Override
    public void manage(Handler handler, Context ctx, Set<RouteRole> routeRoles) throws Exception {
        Role userRole = getUserRole(ctx);
        if (routeRoles.contains(userRole)) {
            handler.handle(ctx);

        } else {
            ctx.status(401).result("Unauthorized");
        }

    }

    public AuthService() {
        super();
    }

    // Gets user role from jwt. If missing, role=ANYONE.
    // if jwt is valid, adds user-id,user-role to context
    // for later use in handlers
    Role getUserRole(Context ctx) {
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
