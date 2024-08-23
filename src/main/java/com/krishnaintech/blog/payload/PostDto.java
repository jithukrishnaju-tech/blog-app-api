package com.krishnaintech.blog.payload;

import com.krishnaintech.blog.entity.Category;
import com.krishnaintech.blog.entity.Comment;
import com.krishnaintech.blog.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
public class PostDto {
    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private CategoryDto category;
    private UserDto user;
    private Set<CommentDto> comments;
}
