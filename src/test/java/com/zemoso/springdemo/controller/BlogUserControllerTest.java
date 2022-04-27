package com.zemoso.springdemo.controller;

import com.zemoso.springdemo.service.BlogServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class BlogUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BlogServiceImpl blogService;

    @InjectMocks
    private BlogUserController blogUserController;


    @BeforeEach
    public void setup() {

        MockitoAnnotations.openMocks(this);
        //this.mockMvc = MockMvcBuilders.standaloneSetup(this.blogAdminController).build();
    }

    @Test
    @WithMockUser(username = "admin",password = "password",roles = {"USER"})
    void testBlogsList() throws Exception {
        this.mockMvc.perform(get("/blogs/user/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("blogs"))
                .andExpect(model().attributeExists("allBlogs"))
                .andExpect(view().name("user-list"));
    }

    @Test
    @WithMockUser(username = "admin",password = "password",roles = {"ADMIN"})
    void testMasterListForAdmin() throws Exception {
        this.mockMvc.perform(get("/blogs/user/list"))
                .andExpect(status().is(403))
                .andExpect(forwardedUrl("/blogs/access-denied"));
    }

    @Test
    @WithMockUser(username = "admin",password = "password",roles = {"USER"})
    void testShowFormForAdd() throws Exception {

        this.mockMvc.perform(get("/blogs/user/showFormForAdd"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-blog-form"));
    }


}