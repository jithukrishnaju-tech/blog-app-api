package com.krishnaintech.blog.service.imp;

import com.krishnaintech.blog.entity.Comment;
import com.krishnaintech.blog.entity.Post;
import com.krishnaintech.blog.exceptions.ResourceNotFoundException;
import com.krishnaintech.blog.payload.CommentDto;
import com.krishnaintech.blog.repository.CommentRepo;
import com.krishnaintech.blog.repository.PostRepo;
import com.krishnaintech.blog.repository.UserRepo;
import com.krishnaintech.blog.service.CommentService;
import com.krishnaintech.blog.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp implements CommentService {
    private final PostRepo postRepo;
    private final ModelMapper modelMapper;
    private final CommentRepo commentRepo;

    public CommentServiceImp(PostRepo postRepo, ModelMapper modelMapper, CommentRepo commentRepo) {
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
        this.commentRepo = commentRepo;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
       Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","PostId",postId));
        Comment comment = modelMapper.map(commentDto,Comment.class);
        comment.setPost(post);
        comment.setUser(comment.getUser());
        Comment savedComment = commentRepo.save(comment);
        return modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment","CommendId",commentId));
        commentRepo.delete(comment);
    }
}
