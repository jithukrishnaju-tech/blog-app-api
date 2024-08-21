package com.krishnaintech.blog.repository;

import com.krishnaintech.blog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
