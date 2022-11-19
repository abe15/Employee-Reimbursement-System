package com.revature.project1.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.project1.models.UserModel;

public class UserDao implements IDao<UserModel> {

    private List<UserModel> users = new ArrayList<>();

    public UserDao() {
        users.add(new UserModel("John", "Smith", "Jonh", "john@domain.com", 1, 1));
        users.add(new UserModel("Susan", "smith", "susan", "susan@domain.com", 2, 2));
    }

    @Override
    public Optional<UserModel> get(long id) {
        return Optional.ofNullable(users.get((int) id));
    }

    @Override
    public List<UserModel> getAll() {
        return users;
    }

    @Override
    public void save(UserModel user) {
        users.add(user);
    }

    @Override
    public void update(UserModel user, String[] params) {

    }

    @Override
    public void delete(UserModel user) {
        users.remove(user);
    }

}