package com.example.demo.services;
import java.util.*;

import com.example.demo.models.Post;
import com.example.demo.payloads.PostDto;
import com.example.demo.payloads.PostResponse;
public interface PostService {
	
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize);
	
	public List<PostDto> getAllPostByUser(Integer userId);
	
	public List<PostDto> getAllPostByCategory(Integer categoryId);
	
	public PostDto createPost(PostDto postDTO,Integer userId ,Integer categoryId);
	
	public PostDto updatePost(PostDto postDto , Integer Id);
	
	public void deletePost(Integer Id);
	
	

}
