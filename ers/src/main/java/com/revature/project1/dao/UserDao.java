package com.revature.project1.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.project1.models.User;

public class UserDao implements IDao<User> {

    private List<User> users = new ArrayList<>();

    public UserDao() {
        users.add(new User("John", "Smith", "Jonh", "john@domain.com", 1, 1));
        users.add(new User("Susan", "smith", "susan", "susan@domain.com", 2, 2));
    }

    @Override
    public Optional<User> get(long id) {
        return Optional.ofNullable(users.get((int) id));
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public void update(User user, String[] params) {

    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }
}