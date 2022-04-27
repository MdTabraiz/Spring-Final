package com.zemoso.springdemo.rest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemoso.springdemo.dto.BlogDTO;
import com.zemoso.springdemo.entity.Blog;
import com.zemoso.springdemo.service.BlogService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

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


    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    @WithMockUser(username = "admin",password = "admin",roles = {"ADMIN,USER"})
    void testSave() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        Map<String,Object> body = new HashMap<>();
        body.put("id",1);
        body.put("title","Employee");
        body.put("authorName","test");
        body.put("content","Software Engineer");

        this.mockMvc.perform(post("/api/blogs")
               .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(body))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(403))
                .andExpect(forwardedUrl("/blogs/access-denied"));
    }

    @Test
    void testGetBlogs() throws Exception {
        this.mockMvc.perform(get("/api/blogs")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @WithMockUser(username = "admin",password = "admin",roles = "ADMIN")
    void testGetBlog() throws Exception {
        this.mockMvc.perform(get("/api/blogs/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "admin",password = "admin",roles = "ADMIN")
    void testDelete() throws Exception{
        this.mockMvc.perform(delete("/api/blogs/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(403));
    }

}