package com.example.demo.services;
import java.util.*;

import com.example.demo.models.Post;
import com.example.demo.payloads.PostDto;
public interface PostService {
	
	public List<PostDto> getAllPost(Integer pageNumber,Integer pageSize);
	
	public List<PostDto> getAllPostByUser(Integer userId);
	
	public List<PostDto> getAllPostByCategory(Integer categoryId);
	
	public PostDto createPost(PostDto postDTO,Integer userId ,Integer categoryId);
	
	public PostDto updatePost(PostDto postDto , Integer Id);
	
	public void deletePost(Integer Id);
	
	

}
