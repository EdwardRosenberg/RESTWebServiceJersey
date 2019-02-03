package org.example.web.controller;

import org.example.dao.UserDao;
import org.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public List<User> getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    public void createUser(User user) {
        userDao.createUser(user);
    }
}