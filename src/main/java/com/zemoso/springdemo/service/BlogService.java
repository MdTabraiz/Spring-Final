package com.zemoso.springdemo.service;

import com.zemoso.springdemo.entity.Blog;

import java.util.List;

public interface BlogService {

    public List<Blog> findAll();

    public Blog findById(int theId);

    public void save(Blog blog);

    public List<Blog> findByAuthor(String name);

    public void deleteById(int theId);
}
