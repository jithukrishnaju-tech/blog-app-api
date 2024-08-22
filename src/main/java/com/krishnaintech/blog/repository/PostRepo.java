package com.krishnaintech.blog.repository;

import com.krishnaintech.blog.entity.Category;
import com.krishnaintech.blog.entity.Post;
import com.krishnaintech.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByTitleContains(String title);
}
