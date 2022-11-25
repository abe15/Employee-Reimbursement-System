package com.revature.project1.models;

public class UserModel {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Integer userId;
    private Integer userRoleId;

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
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

    public UserModel() {

    }

    public UserModel(String firstname, String lastname, String username, String email, String password) {
        this.firstName = firstname;
        this.lastName = lastname;
        this.username = username;
        this.email = email;
        this.password = password;

    }

    @Override
    public String toString() {

        return "[First Name: " + firstName + " Last Name: " + lastName + "]";
    }

}
