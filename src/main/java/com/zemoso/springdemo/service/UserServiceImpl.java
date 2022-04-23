package com.zemoso.springdemo.service;

import com.zemoso.springdemo.dao.UserAndRoleRepository;
import com.zemoso.springdemo.entity.Authorities;
import com.zemoso.springdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserAndRoleRepository userAndRoleRepository;

    @Override
    public void save(User user) {

       Authorities authorities = new Authorities();
        authorities.setAuthority(user.getRole());
        authorities.setUsername(user.getUsername());
        authorities.setUser(user);
        user.setPassword(encodePassword(user.getPassword()));
        userAndRoleRepository.save(authorities);
    }

    @Override
    public boolean findUser(String username) {
        Optional<Authorities> result = userAndRoleRepository.findById(username);
        boolean userExists = false;

        if(result.isPresent()){
            userExists = true;
        }
        return userExists;
    }


    private String encodePassword(String password){
        String encodedPassword = "";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        encodedPassword = bCryptPasswordEncoder.encode(password);
        return encodedPassword;
    }
}
