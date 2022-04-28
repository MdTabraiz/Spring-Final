package com.zemoso.springdemo.rest;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemoso.springdemo.dto.BlogDTO;

import com.zemoso.springdemo.service.BlogService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.web.context.WebApplicationContext;



import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BlogRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    private BlogRestController restController;

    @Mock
    BlogService blogService;


    @Autowired
    private WebApplicationContext context;


    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.openMocks(this);
        this.testSave();
    }

    @Test
    @WithMockUser(username = "admin",password = "admin",roles = "ADMIN")
    void testSave() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        BlogDTO body = new BlogDTO();
        body.setBlogId(1);
        body.setBlogTitle("Title");
        body.setBlogAuthorName("Author");
        body.setBlogContent("Content");

        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();

        this.mockMvc.perform(post("/api/blogs")
                        .content(mapper.writeValueAsString(body)).
                        contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    void testGetBlogs() throws Exception {
        this.mockMvc.perform(get("/api/blogs")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @WithMockUser(username = "admin",password = "admin",roles = "ADMIN")
    void testGetBlog() throws Exception {
        MvcResult result =this.mockMvc.perform(get("/api/blogs/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk()).andReturn();

        Assertions.assertNotNull(result.getResponse());
    }

    @Test
    @WithMockUser(username = "admin",password = "admin",roles = "ADMIN")
    void testGetBlogNotExists() throws Exception {
        MvcResult result =this.mockMvc.perform(get("/api/blogs/100")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404)).andReturn();
    }

    @Test
    @WithMockUser(username = "admin",password = "admin",roles = "ADMIN")
    void testDelete() throws Exception{
        this.mockMvc.perform(delete("/api/blogs/1")
                        .contentType(MediaType.APPLICATION_JSON).with(csrf()))
                .andExpect(status().is(200));
    }

    @Test
    @WithMockUser(username = "admin",password = "admin",roles = "ADMIN")
    void testDeleteNotExists() throws Exception{
        this.mockMvc.perform(delete("/api/blogs/1000")
                        .contentType(MediaType.APPLICATION_JSON).with(csrf()))
                .andExpect(status().is(404));
    }

}