package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
