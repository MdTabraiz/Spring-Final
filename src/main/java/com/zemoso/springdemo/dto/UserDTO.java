package com.zemoso.springdemo.dto;

import com.zemoso.springdemo.entity.User;

import javax.validation.constraints.NotNull;

public class UserDTO {

    @NotNull(message = "Username is required")
    private String userDtoUsername;

    @NotNull(message = "Password is required")
    private String userDtoPassword;

    private int userDtoEnabled =1;
    @NotNull(message = "Select a Role")
    private String userDtoRole;

    public User toEntity(){
        User user = new User();
        user.setUsername(this.userDtoUsername);
        user.setPassword(this.userDtoPassword);
        user.setEnabled(this.userDtoEnabled);
        user.setRole(this.userDtoRole);

        return user;
    }

    public String getUserDtoUsername() {
        return userDtoUsername;
    }

    public void setUserDtoUsername(String userDtoUsername) {
        this.userDtoUsername = userDtoUsername;
    }

    public String getUserDtoPassword() {
        return userDtoPassword;
    }

    public void setUserDtoPassword(String userDtoPassword) {
        this.userDtoPassword = userDtoPassword;
    }

    public int getUserDtoEnabled() {
        return userDtoEnabled;
    }

    public void setUserDtoEnabled(int userDtoEnabled) {
        this.userDtoEnabled = userDtoEnabled;
    }

    public String getUserDtoRole() {
        return userDtoRole;
    }

    public void setUserDtoRole(String userDtoRole) {
        this.userDtoRole = userDtoRole;
    }
}
