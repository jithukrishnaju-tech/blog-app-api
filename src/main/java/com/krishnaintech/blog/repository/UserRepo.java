package com.krishnaintech.blog.repository;

import com.krishnaintech.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
