package com.zemoso.springdemo.dao;

import com.zemoso.springdemo.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog,Integer> {
}
