package com.zemoso.springdemo.dao;

import com.zemoso.springdemo.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Integer> {
    public List<Blog> findByAuthorName(String name);
}
