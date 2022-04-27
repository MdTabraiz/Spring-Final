package com.zemoso.springdemo.controller;


import com.zemoso.springdemo.dto.UserDTO;

import com.zemoso.springdemo.service.UserServiceImpl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SignUpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private SignUpController signUpController;

    @BeforeEach
    public void setup() {

        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.signUpController).build();
    }

    @Test
    void testShowSignupForm() throws Exception {
        this.mockMvc.perform(get("/blogs/signup/showFormForSignup"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("theuser"))
                .andExpect(view().name("signup"));
    }

    @Test
    void testSaveUserWithNull() throws Exception {

        this.mockMvc.perform(post("/blogs/signup/save").flashAttr("theuser",new UserDTO()))
                .andExpect(status().isOk())
                .andExpect(view().name("signup"));
    }

    @Test
    void testSaveUser() throws Exception {

        UserDTO userDTO = new UserDTO();
        userDTO.setUserDtoUsername("Test");
        userDTO.setUserDtoPassword("pass");
        userDTO.setUserDtoEnabled(1);
        userDTO.setUserDtoRole("ROLE_USER");

        this.mockMvc.perform(post("/blogs/signup/save").flashAttr("theuser",userDTO))
                .andExpect(redirectedUrl("/blogs/showMyLoginPage"));
    }

}