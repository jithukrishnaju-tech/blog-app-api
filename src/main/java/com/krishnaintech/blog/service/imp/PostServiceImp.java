package com.krishnaintech.blog.service.imp;

import com.krishnaintech.blog.entity.Category;
import com.krishnaintech.blog.entity.Post;
import com.krishnaintech.blog.entity.User;
import com.krishnaintech.blog.exceptions.ResourceNotFoundException;
import com.krishnaintech.blog.payload.PostDto;
import com.krishnaintech.blog.payload.PostResponse;
import com.krishnaintech.blog.repository.CategoryRepo;
import com.krishnaintech.blog.repository.PostRepo;
import com.krishnaintech.blog.repository.UserRepo;
import com.krishnaintech.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class PostServiceImp implements PostService {
    private final PostRepo postRepo;
    private final ModelMapper modelMapper;
    private final CategoryRepo categoryRepo;
    private final UserRepo userRepo;
    public PostServiceImp(PostRepo postRepo, ModelMapper modelMapper, CategoryRepo categoryRepo, UserRepo userRepo) {
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
        this.categoryRepo = categoryRepo;
        this.userRepo = userRepo;
    }

    @Override
    public PostDto createPost(PostDto postDto, Integer categoryId, Integer userId) {
        Post post = modelMapper.map(postDto, Post.class);
        post.setAddedDate(new Date());
        post.setImageName("image.png");
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","userId", userId));
        post.setCategory(category);
        post.setUser(user);
        Post postSave = postRepo.save(post);
        return modelMapper.map(postSave, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost = postRepo.save(post);
        return modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","PostId",postId));
        postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
        Sort sort = Objects.equals(sortDirection, "ascending") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> allPages = postRepo.findAll(pageable);
        List<Post> posts = allPages.getContent();
        PostResponse postResponse = new PostResponse();
        List<PostDto> postDto = posts.stream().map((post) -> modelMapper.map(post,PostDto.class)).toList();
        postResponse.setContent(postDto);
        postResponse.setPageNumber(allPages.getNumber());
        postResponse.setPageSize(allPages.getSize());
        postResponse.setTotalElements(allPages.getTotalElements());
        postResponse.setTotalPages(allPages.getTotalPages());
        postResponse.setLastPage(allPages.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","PostId",postId));
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow( () -> new ResourceNotFoundException("Category","categoryId",categoryId));
        List<Post> postByCategory = postRepo.findByCategory(category);
        return postByCategory.stream().map((post) ->modelMapper.map(post, PostDto.class)).toList();
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
        List<Post> posts = postRepo.findByUser(user);
        return posts.stream().map((post) ->modelMapper.map(post, PostDto.class)).toList();
    }

    @Override
    public List<PostDto> getPostBySearch(String search) {
        List<Post> post = postRepo.findByTitleContains(search);
        return post.stream().map((posts) -> modelMapper.map(posts, PostDto.class)).toList();
    }
}
