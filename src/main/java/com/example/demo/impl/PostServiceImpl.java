package com.example.demo.impl;


import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Repositories.CategoryRepo;
import com.example.demo.Repositories.PostRepo;
import com.example.demo.Repositories.UserRepo;
import com.example.demo.models.Category;
import com.example.demo.models.Post;
import com.example.demo.models.User;
import com.example.demo.payloads.CategoryDto;
import com.example.demo.payloads.PostResponse;
import com.example.demo.payloads.PostDto;
import com.example.demo.services.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private UserRepo userRepo;


	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize) {
		// TODO Auto-generated method stub
		Pageable p = PageRequest.of(pageNumber, pageSize);
		Page<Post> allPagePosts = this.postRepo.findAll(p);
		List<Post> allPosts = allPagePosts.getContent();
		List<PostDto> allPostDto = allPagePosts.stream().map(post-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setPostDto(allPostDto);
		postResponse.setPageNumber(allPagePosts.getNumber());
		postResponse.setTotalPages(allPagePosts.getTotalPages());
		postResponse.setIsLast(allPagePosts.isLast());
	    return postResponse;
	}

	@Override
	public List<PostDto> getAllPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","UserId",userId));
		List<Post> allPostByUser = this.postRepo.getByUser(user);
		List<PostDto> allPostDtosByUser = allPostByUser.stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return allPostDtosByUser;
	}

	@Override
	public List<PostDto> getAllPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","categoryId",categoryId));
		List<Post> allPostByCategory = this.postRepo.getByCategory(category);
		List<PostDto> allPostDtosByCategory = allPostByCategory.stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return allPostDtosByCategory;
		
	}

	@Override
	public PostDto createPost(PostDto postDTO, Integer userId, Integer categoryId) {
		// TODO Auto-generated method stub
	    Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException());
	   
	    User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","UserID",userId));
		
		Post post = this.modelMapper.map(postDTO, Post.class);
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost = this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Integer Id) {
		// TODO Auto-generated method stub

	}
	
	

}
