package com.zemoso.springdemo.controller;


import com.zemoso.springdemo.dao.BlogRepository;
import com.zemoso.springdemo.dto.BlogDTO;
import com.zemoso.springdemo.dto.UserDTO;
import com.zemoso.springdemo.entity.Blog;
import com.zemoso.springdemo.service.BlogServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
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


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
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

    @Mock
    private BlogRepository blogRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @BeforeEach
    public void setup() throws Exception {

        MockitoAnnotations.openMocks(this);
       mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    @Test
    @WithMockUser(username = "admin",password = "password",roles = {"ADMIN"})
    void testMasterList() throws Exception {
        this.mockMvc.perform(get("/blogs/admin/master-list"))
                .andExpect(status().isOk())
                .andExpect(view().name("blog-list"));
    }

    @Test
    @WithMockUser(username = "admin",password = "password",roles = {"ADMIN"})
    void testShowFormForUpdate() throws Exception {

        Blog blog = new Blog();

        blog.setId(1);
        blog.setTitle("Test Title");
        blog.setAuthorName("Test Author");
        blog.setContent("Test Content");

        Mockito.when(blogService.findById(1)).thenReturn(blog);

        this.mockMvc.perform(get("/blogs/admin/showFormForUpdate")
                        .param("blogId","1"))
                .andExpect(status().isOk())
                .andExpect(view().name("blog-form"));
    }

    @Test
    @WithMockUser(username = "admin",password = "password",roles = {"ADMIN"})
    void testSaveBlog() throws Exception {

        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setBlogId(1);
        blogDTO.setBlogTitle("Test");
        blogDTO.setBlogAuthorName("Test");
        blogDTO.setBlogContent("Test");

        this.mockMvc.perform(post("/blogs/admin/save").flashAttr("theblog",blogDTO))
                .andExpect(redirectedUrl("/blogs/admin/master-list"));
    }

    @Test
    @WithMockUser(username = "admin",password = "password",roles = {"ADMIN"})
    void testDeleteBlog() throws Exception {

        this.testSaveBlog();
         this.mockMvc.perform(get("/blogs/admin/delete").param("blogId","1"))
                .andExpect(status().is(302)).andExpect(redirectedUrl("/blogs/admin/master-list"));
    }

}