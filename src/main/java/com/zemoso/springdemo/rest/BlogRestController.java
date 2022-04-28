package com.zemoso.springdemo.rest;

import com.zemoso.springdemo.dto.BlogDTO;
import com.zemoso.springdemo.entity.Blog;
import com.zemoso.springdemo.exceptionhandler.BlogNotFoundException;
import com.zemoso.springdemo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogRestController {


    @Autowired
    private BlogService blogService;

    @GetMapping("/blogs")
    public List<Blog> getBlogs(){
        return blogService.findAll();
    }

    @GetMapping("/blogs/{blogId}")
    public Blog getBlog(@PathVariable int blogId){

        Blog blog =  blogService.findById(blogId);

        if(blog==null){
            throw new BlogNotFoundException("No Blog found with the id - "+blogId);
        }
        return blog;
    }

    @PostMapping("/blogs")
    public void addBlog(@RequestBody BlogDTO blog){
        blogService.save(blog.toEntity());
    }

    @PutMapping("/blogs")
    public void updateBlog(@RequestBody BlogDTO blog){
        blogService.save(blog.toEntity());
    }

    @DeleteMapping("/blogs/{blogId}")
    public String deleteBlog(@PathVariable int blogId){
        Blog blog =  blogService.findById(blogId);

        if(blog==null){
            throw new BlogNotFoundException("No Blog found with the id - "+blogId);
        }

        blogService.deleteById(blogId);

        return "Blog deleted with id "+blogId;
    }

}
