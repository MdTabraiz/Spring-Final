package com.zemoso.springdemo.entity;

import javax.persistence.*;
import java.util.logging.Logger;


@Entity
@Table(name = "authorities")
public class Authorities {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "authority")
    private String authority;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="username")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Authorities(){
        Logger logger = Logger.getLogger(getClass().getName());
        logger.info("Authorities Instantiated");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
