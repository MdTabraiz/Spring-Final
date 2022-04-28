package com.zemoso.springdemo.dto;

import com.zemoso.springdemo.entity.Blog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class BlogDTOTest {

    @Test
    void testToEntity(){
        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setBlogId(1);
        blogDTO.setBlogTitle("Test Title");
        blogDTO.setBlogAuthorName("Test Author");
        blogDTO.setBlogContent("Test Content");

        Blog blog = blogDTO.toEntity();

        Assertions.assertEquals(blog.getId(),blogDTO.getBlogId());
        Assertions.assertEquals(blog.getTitle(),blogDTO.getBlogTitle());
        Assertions.assertEquals(blog.getAuthorName(),blogDTO.getBlogAuthorName());
        Assertions.assertEquals(blog.getContent(),blogDTO.getBlogContent());


    }

}