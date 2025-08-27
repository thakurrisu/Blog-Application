package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Comment;
import com.example.demo.payloads.CommentDto;
import com.example.demo.services.CommentService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/")
@Tag(name = "Comment APIs")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@PostMapping("posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto , @PathVariable Integer postId)
	{
		CommentDto savedComment = this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(savedComment,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("comments/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable Integer commentId)
	{
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<String>("Comment Deleted Successfully",HttpStatus.OK);
	}
	
}
