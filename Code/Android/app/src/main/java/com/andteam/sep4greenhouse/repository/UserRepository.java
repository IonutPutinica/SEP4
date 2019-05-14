package com.andteam.sep4greenhouse.repository;

import com.andteam.sep4greenhouse.model.UserProfileDTO;

public class UserRepository {

    // Access UserRepository instance
    private static UserRepository instance;
    private String email;

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new UserRepository();
            return instance;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}