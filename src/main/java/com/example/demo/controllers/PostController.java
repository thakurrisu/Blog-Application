package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payloads.PostDto;
import com.example.demo.payloads.PostResponse;
import com.example.demo.services.PostService;
import java.util.List;
@RestController
@RequestMapping("/api/v1/")
public class PostController {

	@Autowired
	private PostService postService;
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value ="pageNumber",defaultValue="0",required=false)Integer pageNumber , 
			@RequestParam(value="pageSize",defaultValue ="5",required=false)Integer pageSize){
		PostResponse allPostResponse = this.postService.getAllPost(pageNumber,pageSize);
		return new ResponseEntity<>(allPostResponse,HttpStatus.OK);
	}
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createpost(@RequestBody PostDto postDto , @PathVariable Integer userId , @PathVariable Integer categoryId){
		PostDto postDtoResponse = this.postService.createPost(postDto , userId , categoryId);
		
		return new ResponseEntity<PostDto>(postDtoResponse,HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId)
	{
	   List<PostDto> postDto = this.postService.getAllPostByUser(userId);
	   return new ResponseEntity<List<PostDto>>(postDto , HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId)
	{
	   List<PostDto> postDto = this.postService.getAllPostByCategory(categoryId);
	   return new ResponseEntity<List<PostDto>>(postDto , HttpStatus.OK);
	}
}
