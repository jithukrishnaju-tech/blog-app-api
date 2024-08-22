package com.krishnaintech.blog.repository;

import com.krishnaintech.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
