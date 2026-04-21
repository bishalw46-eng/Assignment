package com.learn.service;

import java.sql.SQLException;

import com.learn.dao.UserDAO;
import com.learn.model.User;

public class AuthService {

    private final UserDAO userDAO;

    public AuthService() {
        this.userDAO = new UserDAO();
    }

    public User authenticate(String username, String password) {
        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            return null;
        }

        try {
            return userDAO.findByCredentials(username.trim(), password.trim());
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
