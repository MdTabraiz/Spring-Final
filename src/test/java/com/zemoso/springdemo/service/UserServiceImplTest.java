package com.zemoso.springdemo.service;


import com.zemoso.springdemo.dao.UserAndRoleRepository;
import com.zemoso.springdemo.entity.Authorities;

import com.zemoso.springdemo.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;


@SpringBootTest
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserAndRoleRepository repository;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave(){

        User user = new User();
        user.setUsername("Test User");
        user.setEnabled(1);
        user.setPassword(new BCryptPasswordEncoder().encode("Password"));
        user.setRole("USER_ROLE");

        Authorities authorities = new Authorities();
        authorities.setAuthority(user.getRole());
        authorities.setUsername(user.getUsername());
        authorities.setUser(user);


        userService.save(user);
        Mockito.when(repository.save(authorities)).thenReturn(authorities);

        Assertions.assertEquals(user.getUsername(),authorities.getUsername());
        Assertions.assertEquals(user.getRole(),authorities.getAuthority());

    }

    @Test
    void testFindUser(){

        Authorities authorities = new Authorities();
        authorities.setUsername("Test User");
        authorities.setAuthority("ROLE_ADMIN");

        Mockito.when(repository.findById("Test User")).thenReturn(Optional.of(authorities));

        userService.findUser("Test User");

        Mockito.verify(repository).findById("Test User");

    }

}