package com.grupo1.myapp.data.model;

public class RegisteredUser {
    private String username;
    private String email;

    public RegisteredUser(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
