package com.example.demo.payloads;

import java.util.HashSet;
import java.util.Set;

import com.example.demo.models.Category;
import com.example.demo.models.Comment;
import com.example.demo.models.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private Integer postId;
	
	private String content;
	
    private String title;

    private UserDto user;
    
    private CategoryDto category;
    
    private Set<CommentDto> comments = new HashSet<CommentDto>();
    
}
