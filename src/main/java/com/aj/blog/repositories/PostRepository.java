package com.aj.blog.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aj.blog.entities.Category;
import com.aj.blog.entities.Post;
import com.aj.blog.entities.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

	public Page<Post> findByUser(User user, Pageable peagable );

	public Page<Post> findByCategory(Category category, Pageable peagable );
	
	public List<Post> findByPostTittleContaining(String postTittle);

}
