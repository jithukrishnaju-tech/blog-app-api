package com.krishnaintech.blog.service;

import com.krishnaintech.blog.payload.CommentDto;

public interface CommentService {
    public CommentDto createComment(CommentDto commentDto, Integer postId);
    public CommentDto deleteComment(Integer commentId);
}
