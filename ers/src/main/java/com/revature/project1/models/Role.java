package com.revature.project1.models;

import io.javalin.core.security.RouteRole;

public enum Role implements RouteRole {
    ANYONE, EMPLOYEE, MANAGER, ADMIN;

}