package com.zemoso.springdemo.service;

import com.zemoso.springdemo.dao.BlogRepository;
import com.zemoso.springdemo.entity.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Blog findById(int theId) {
        Optional<Blog> result = blogRepository.findById(theId);

        Blog blog = null;

        if(result.isPresent()){
            blog = result.get();
        }

        return blog;
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public void deleteById(int theId) {
        blogRepository.deleteById(theId);
    }
}
