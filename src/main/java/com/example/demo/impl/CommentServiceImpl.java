package com.example.demo.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Repositories.CommentRepo;
import com.example.demo.Repositories.PostRepo;
import com.example.demo.models.Comment;
import com.example.demo.models.Post;
import com.example.demo.payloads.CommentDto;
import com.example.demo.services.CommentService;
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelmapper;
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer PostId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(PostId).orElseThrow(() ->
		new ResourceNotFoundException("Post","PostId",PostId));
		Comment comment = this.modelmapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedComment = this.commentRepo.save(comment);
		
		return this.modelmapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
	   Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment Name", "Id",commentId));
	   this.commentRepo.delete(comment);
	}

}
