package com.zemoso.springdemo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new LoginController()).build();
    }

    @Test
    void testLoginPage() throws Exception {
        this.mockMvc.perform(get("/blogs/showMyLoginPage"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }


    @Test
    @WithMockUser(username = "admin",password = "password",authorities ={"ADMIN,USER"} )
    void loginPageRedirect() throws Exception{
        Authentication authentication = Mockito.mock(Authentication.class);

        Collection authorities = Collections.emptyList();

        Mockito.when(authentication.getAuthorities()).thenReturn(authorities);

        this.mockMvc.perform(get("/blogs/success"))
                .andExpect(status().isOk())
                .andExpect(view().name("/blogs/user/list"));
    }



}