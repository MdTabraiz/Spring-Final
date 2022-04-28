package com.zemoso.springdemo.entity;

import com.zemoso.springdemo.dto.BlogDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class BlogTest {

    @Test
    void testToDTO(){
        Blog blog = new Blog();
        blog.setId(1);
        blog.setTitle("Test Title");
        blog.setAuthorName("Test Author");
        blog.setContent("Test Content");

        BlogDTO blogDTO = blog.toDto();

        Assertions.assertEquals(blogDTO.getBlogId(),blog.getId());
        Assertions.assertEquals(blogDTO.getBlogTitle(),blog.getTitle());
        Assertions.assertEquals(blogDTO.getBlogAuthorName(),blog.getAuthorName());
        Assertions.assertEquals(blogDTO.getBlogContent(),blog.getContent());


    }

}