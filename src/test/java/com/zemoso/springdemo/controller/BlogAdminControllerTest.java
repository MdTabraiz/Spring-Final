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

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class BlogAdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BlogServiceImpl blogService;

    @InjectMocks
    private BlogAdminController blogAdminController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser(username = "admin",password = "password",roles = {"ADMIN"})
    void testMasterList() throws Exception {
        this.mockMvc.perform(get("/blogs/admin/master-list"))
                .andExpect(status().isOk())
                .andExpect(view().name("blog-list"));
    }

    @Test
    @WithMockUser(username = "admin",password = "password",roles = {"USER"})
    void testMasterListForUser() throws Exception {
        this.mockMvc.perform(get("/blogs/admin/master-list"))
                .andExpect(status().is(403))
                .andExpect(forwardedUrl("/blogs/access-denied"));
    }

    @Test
    @WithMockUser(username = "admin",password = "password",roles = {"ADMIN"})
    void testShowFormForAdd() throws Exception {
        this.mockMvc.perform(get("/blogs/admin/showFormForAdd"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("theblog"))
                .andExpect(view().name("blog-form"));
    }

//    @Test
//    @WithMockUser(username = "admin",password = "password",roles = {"ADMIN"})
//    void testSaveBlogs() throws Exception{
//        this.mockMvc.perform(post("/blogs/admin/save"))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.model().attributeExists("theblog"))
//                .andExpect(view().name("blog-list"));
//    }

//    @Test
//    @WithMockUser(username = "admin",password = "password",roles = {"ADMIN"})
//    void testShowFormForUpdate() throws Exception {
//        this.mockMvc.perform(get("/blogs/admin/showFormForUpdate")
//                        .param("blogId","0"))
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("theblog"))
//                .andExpect(view().name("blog-form"));
//    }

    @Test
    @WithMockUser(username = "admin",password = "password",roles = {"ADMIN"})
    void testDelete() throws Exception {
        this.mockMvc.perform(get("/blogs/admin/delete")
                        .param("blogId","0"))
                .andExpect(status().is(400));
    }
}