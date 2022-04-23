package com.zemoso.springdemo.dto;

import com.zemoso.springdemo.entity.User;

public class UserDTO {

    private String username;

    private String password;

    private int enabled=1;

    private String role;

    public User toEntity(){
        User user = new User();
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setEnabled(this.enabled);
        user.setRole(this.role);

        return user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
