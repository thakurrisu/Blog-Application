package com.example.demo.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.User;
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
 
	Optional<User> findByName(String name);
}
