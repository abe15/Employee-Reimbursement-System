package com.revature.project1.services;

import java.util.Optional;

import com.revature.project1.models.UserModel;

public interface IUserService {

    public boolean registerUser(UserModel user);

    public boolean login(String username, String password);

    public Optional<UserModel> getUserById(int id);

    public boolean updateUser(UserModel user);

    public boolean deleteUser(int id);

}
