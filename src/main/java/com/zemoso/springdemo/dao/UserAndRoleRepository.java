package com.zemoso.springdemo.dao;

import com.zemoso.springdemo.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAndRoleRepository extends JpaRepository<Authorities,String> {
}
