package com.revature.project1.service.user.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.project1.models.UserModel;
import com.revature.project1.service.user.IUserDao;

public class UserDaoList implements IUserDao {

    private List<Optional<UserModel>> users = new ArrayList<>();

    @Override
    public Optional<UserModel> findByUserName(String userName) {
        for (Optional<UserModel> user : users) {
            if (user.isPresent()) {

                if (user.get().getUsername().equals(userName)) {

                    return user;
                }

            }

        }
        return Optional.empty();

    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        for (Optional<UserModel> user : users) {
            if (user.isPresent() && user.get().getEmail().equals(email)) {
                return user;
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Optional<UserModel>> getAllUsers() {
        // TODO Auto-generated method stub
        return users;
    }

    @Override
    public void save(UserModel user) {
        users.add(Optional.of(user));

    }

}
