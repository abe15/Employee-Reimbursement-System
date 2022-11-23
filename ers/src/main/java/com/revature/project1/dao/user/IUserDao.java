package com.revature.project1.dao.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.project1.models.UserModel;

public interface IUserDao {

    public Optional<UserModel> findByUserName(String userName);

    public Optional<UserModel> findByEmail(String email);

    public List<UserModel> getAllUsers();

    // dd
    public int save(UserModel user);

}