package com.zemoso.springdemo.controller;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new LoginController()).build();
    }

    @Test
    void testLoginPage() throws Exception {
        this.mockMvc.perform(get("/blogs/showMyLoginPage"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }


//    @Test
//    @WithMockUser
//    void loginPageRedirect() throws Exception{
//
////        Authentication authentication = Mockito.mock(Authentication.class);
////
////        Collection<? extends GrantedAuthority> grantedAuthorities = Lists.newArrayList(
////                new SimpleGrantedAuthority("ROLE_USER"));
////
////
////        System.out.println(authentication.getAuthorities());
////
////        Mockito.doReturn(grantedAuthorities).when(authentication).getAuthorities();
//
//        this.mockMvc.perform(post("/blogs/success"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("/blogs/user/list"));
//    }



}