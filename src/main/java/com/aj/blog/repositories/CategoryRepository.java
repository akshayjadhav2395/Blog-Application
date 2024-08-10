package com.aj.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aj.blog.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	

}
