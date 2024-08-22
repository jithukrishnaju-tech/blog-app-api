package com.krishnaintech.blog.controller;

import com.krishnaintech.blog.payload.CommentDto;
import com.krishnaintech.blog.service.CommentService;
import com.krishnaintech.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/post/{postid}")
    public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto, @PathVariable("postid") Integer postId){
        CommentDto commentSaved = commentService.createComment(commentDto, postId);
        return new ResponseEntity<>(commentSaved, HttpStatus.OK);
    }
}
