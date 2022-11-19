package com.revature.project1.models;

public class User {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private final int userId;
    private final int userRoleId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public User(String firstName, String lastName, String username, String email, int userId, int userRoleId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.userId = userId;
        this.userRoleId = userRoleId;
    }

}