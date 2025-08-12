package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Category;
@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
