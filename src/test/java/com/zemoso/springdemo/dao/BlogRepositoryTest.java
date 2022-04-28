package com.zemoso.springdemo.dao;

import com.zemoso.springdemo.entity.Blog;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class BlogRepositoryTest {

    @Autowired
    private BlogRepository blogRepository;

    @BeforeEach
    public void setUp(){
        Blog blog = new Blog();
        blog.setId(1);
        blog.setTitle("Test Title");
        blog.setAuthorName("Test Author");
        blog.setContent("Test Content");

        blogRepository.save(blog);
    }


    @Test
    void findBlogsByAuthorName() {

        List<Blog> listOfBlogs = blogRepository.findByAuthorName("Test Author");

        Assertions.assertThat(listOfBlogs).isNotEmpty();
    }

    @Test
    void findBlogsByFalseAuthorName() {

        List<Blog> listOfBlogs = blogRepository.findByAuthorName("Test False Author");

        Assertions.assertThat(listOfBlogs).isEmpty();
    }


}