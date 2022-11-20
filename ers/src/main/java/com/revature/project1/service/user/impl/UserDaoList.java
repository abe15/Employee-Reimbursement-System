package com.revature.project1.service.user.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.project1.models.UserModel;
import com.revature.project1.service.user.IUserDao;

public class UserDaoList implements IUserDao {

    private List<UserModel> users = new ArrayList<>();

    @Override
    public Optional<UserModel> findByUserName(String userName) {
        for (UserModel user : users) {
            if (user.getUsername().equals(userName)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        for (UserModel user : users) {
            if (user.getEmail().equals(email)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<UserModel> getAllUsers() {
        // TODO Auto-generated method stub
        return users;
    }

    @Override
    public void save(UserModel user) {
        users.add(user);

    }

}
