package com.revature.project1.services;

import com.revature.project1.dao.user.IUserDao;
import com.revature.project1.dao.user.impl.UserDaoSQL;
import com.revature.project1.models.UserModel;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceImpl implements IUserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private static IUserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoSQL();
    }

    public UserServiceImpl(IUserDao ud) {
        this.userDao = ud;
    }

    @Override
    public boolean registerUser(UserModel user) {

        logger.info("UserServiceImpl::register() called. Creating new user...");

        int id = userDao.save(user);
        // 3. return true if id exists else return false
        logger.info("Received from DAO. New ID: " + id);

        // if user created id nonzero
        return (id != 0) ? true : false;
    }

    @Override
    public boolean login(String username, String password) {
        logger.info("UserServiceImpl::login() called. User log in...");

        Optional<UserModel> user = userDao.findByUserName(username);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            logger.info("UserServiceImpl::login() called. User name and password match found.");

            return true;
        }

        logger.info("UserServiceImpl::login() called. User name and password match NOT found.");
        return false;

    }

    @Override
    public Optional<UserModel> getUserById(int id) {

        return null;
    }

    @Override
    public boolean updateUser(UserModel user) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteUser(int id) {
        // TODO Auto-generated method stub
        return false;
    }

}
