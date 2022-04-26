package com.zemoso.springdemo.service;

import com.zemoso.springdemo.dao.BlogRepository;
import com.zemoso.springdemo.entity.Blog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class BlogServiceTest {

    @InjectMocks
    private BlogServiceImpl blogService;

    @Mock
    private BlogRepository blogRepository;


    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById(){
        Blog blog = new Blog();

        blog.setId(1);
        blog.setTitle("Test Title");
        blog.setAuthorName("Test Author");
        blog.setContent("Test Content");

        Mockito.when(blogRepository.findById(1)).
                thenReturn(Optional.of(blog));

        Blog userBlog = blogService.findById(1);
        Assertions.assertEquals("Test Title",userBlog.getTitle());
        Assertions.assertEquals("Test Author",userBlog.getAuthorName());
        Assertions.assertEquals("Test Content",userBlog.getContent());

    }


}