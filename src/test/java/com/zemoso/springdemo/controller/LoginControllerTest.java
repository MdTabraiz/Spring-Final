package com.zemoso.springdemo.controller;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//
//@WebMvcTest(controllers = LoginController.class)
@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
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
    void testAccessDenied() throws Exception{
        this.mockMvc.perform(get("/blogs/access-denied"))
                .andExpect(status().isOk())
                .andExpect(view().name("access-denied"));
    }
}