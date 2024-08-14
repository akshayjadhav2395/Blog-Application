package com.aj.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aj.blog.entities.Comment;
import com.aj.blog.entities.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	public List<Comment> findByUser(User user);
	
}
