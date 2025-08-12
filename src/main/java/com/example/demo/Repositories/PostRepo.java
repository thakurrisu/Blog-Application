package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.example.demo.models.Category;
import com.example.demo.models.Post;
import com.example.demo.models.User;
@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> getByUser(User user);
	
	List<Post> getByCategory(Category category);
}
