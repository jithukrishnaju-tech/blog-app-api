package com.krishnaintech.blog.controller;

import com.krishnaintech.blog.payload.ApiResponseDto;
import com.krishnaintech.blog.payload.PostDto;
import com.krishnaintech.blog.payload.PostResponse;
import com.krishnaintech.blog.service.PostService;
import com.krishnaintech.blog.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<PostDto> addPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
        PostDto post = postService.createPost(postDto,userId, categoryId);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> findByUser(@PathVariable Integer userId){
        List<PostDto> post = postService.getPostByUser(userId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getByCategory(@PathVariable Integer categoryId){
        List<PostDto> posts = postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pagenumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                   @RequestParam(value = "pagesize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                   @RequestParam(value = "sortby", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                   @RequestParam(value = "sortdirection", defaultValue = AppConstants.SORT_DIRECTION, required = false) String sortDirection){
        PostResponse posts = postService.getAllPost(pageNumber, pageSize, sortBy, sortDirection);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto post = postService.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponseDto> deletePost(@PathVariable Integer postId){
        postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponseDto("Post deleted Succesfully", true), HttpStatus.OK);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
        PostDto updatedPost = postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<List<PostDto>> searchByPost(@PathVariable("title") String title){
        List<PostDto> posts = postService.getPostBySearch(title);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
