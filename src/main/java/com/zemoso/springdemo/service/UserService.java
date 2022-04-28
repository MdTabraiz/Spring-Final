package com.zemoso.springdemo.service;



import com.zemoso.springdemo.entity.User;

public interface UserService {

    public void save(User user);

    public boolean findUser(String username);

}
