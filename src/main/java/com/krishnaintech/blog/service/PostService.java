package com.krishnaintech.blog.service;

import com.krishnaintech.blog.entity.Category;
import com.krishnaintech.blog.payload.PostDto;
import com.krishnaintech.blog.payload.PostResponse;

import java.util.List;

public interface PostService {
    public PostDto createPost(PostDto postDto, Integer categoryId, Integer userId);
    public PostDto updatePost(PostDto postDto, Integer postId);
    public void deletePost(Integer postId);
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);
    public PostDto getPostById(Integer postId);
    public List<PostDto> getPostByCategory(Integer categoryId);
    public List<PostDto> getPostByUser(Integer userId);
    public List<PostDto> getPostBySearch(String search);
}
