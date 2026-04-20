package com.learn.service;

public class AuthService {

    private static final String ADMIN_USER = "admin";
    private static final String ADMIN_PASS = "admin123";

    public boolean login(String username, String password) {
        return ADMIN_USER.equals(username) && ADMIN_PASS.equals(password);
    }
}
