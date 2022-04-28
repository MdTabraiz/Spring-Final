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

import java.util.ArrayList;
import java.util.List;
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
    void testFindAll(){

        List<Blog> blogs = new ArrayList<>();
        Blog blog = new Blog();

        blog.setId(1);
        blog.setTitle("Test Title");
        blog.setAuthorName("Test Author");
        blog.setContent("Test Content");

        blogs.add(blog);

        Mockito.when(blogRepository.findAll()).thenReturn(blogs);

        List<Blog> allBlogs = blogService.findAll();

        Mockito.verify(blogRepository).findAll();

        Assertions.assertEquals(blogs,allBlogs);
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

    @Test
    void testSave(){
        Blog blog = new Blog();
        blog.setId(1);
        blog.setTitle("Test Title");
        blog.setAuthorName("Test Author");
        blog.setContent("Test Content");

        Mockito.when(blogRepository.save(blog)).thenReturn(blog);

        blogService.save(blog);

        Mockito.verify(blogRepository).save(blog);
    }

    @Test
    void testFindByAuthor(){
        List<Blog> blogs = new ArrayList<>();
        Blog blog = new Blog();

        blog.setId(1);
        blog.setTitle("Test Title");
        blog.setAuthorName("Test Author");
        blog.setContent("Test Content");

        blogs.add(blog);

        Mockito.when(blogRepository.findByAuthorName("Test Author")).thenReturn(blogs);

        List<Blog> authorBlogs = blogService.findByAuthor("Test Author");

        Mockito.verify(blogRepository).findByAuthorName("Test Author");

        Assertions.assertEquals(blogs,authorBlogs);

    }

    @Test
    void testDelete(){

        Blog blog = new Blog();
        blog.setId(1);
        blog.setTitle("Test Title");
        blog.setAuthorName("Test Author");
        blog.setContent("Test Content");

       blogService.deleteById(1);
       Mockito.verify(blogRepository).deleteById(1);

    }

}