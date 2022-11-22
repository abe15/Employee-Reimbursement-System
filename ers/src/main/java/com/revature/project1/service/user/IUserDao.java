package com.revature.project1.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.project1.models.UserModel;

public interface IUserDao {

    public Optional<UserModel> findByUserName(String userName);

    public Optional<UserModel> findByEmail(String email);

    public List<Optional<UserModel>> getAllUsers();

    public void save(UserModel user);

}