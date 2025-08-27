package com.example.demo.services;

import com.example.demo.payloads.CommentDto;

public interface CommentService {

	//Create
	public CommentDto createComment(CommentDto commentDto , Integer PostId);
	
	//delete
	public void deleteComment(Integer commentId);
}
