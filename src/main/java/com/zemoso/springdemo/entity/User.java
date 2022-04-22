package com.zemoso.springdemo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.logging.Logger;


@Entity
@Table(name = "users")
public class User {


    @Id
    @NotNull(message = "Username is required")
    @Column(name = "username")
    private String username;

    @NotNull(message = "Password is required")
    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private int enabled=1;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Transient
    private String role;

    public User(){
        Logger logger = Logger.getLogger(getClass().getName());
        logger.info("User Instantiated");
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

}
