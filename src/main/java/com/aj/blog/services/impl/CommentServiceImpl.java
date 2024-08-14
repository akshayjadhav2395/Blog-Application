package com.aj.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aj.blog.entities.Comment;
import com.aj.blog.entities.Post;
import com.aj.blog.entities.User;
import com.aj.blog.exceptions.ResourceNotFoundException;
import com.aj.blog.payload.CommentDto;
import com.aj.blog.repositories.CommentRepository;
import com.aj.blog.repositories.PostRepository;
import com.aj.blog.repositories.UserRepository;
import com.aj.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	public CommentDto createComment(CommentDto commentDto, int postId, int uId) {
		
		Comment comment = this.mapper.map(commentDto, Comment.class);
		
		Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
		
		User user = this.userRepository.findById(uId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", uId));
		
		comment.setPost(post);
		comment.setUser(user);
		
		Comment savedComment = this.commentRepository.save(comment);
		
		CommentDto dto = this.mapper.map(savedComment, CommentDto.class);
		
		return dto;
	}


	@Override
	public List<CommentDto> getAllComments() {
		
		List<Comment> list = this.commentRepository.findAll();
		
		List<CommentDto> commentDtos = list.stream().map((com)-> this.mapper.map(com, CommentDto.class)).collect(Collectors.toList());
		
		return commentDtos;
	}

	@Override
	public CommentDto getCommentById(int id) {

		Comment comment = this.commentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Comment", "id", id));
		
		CommentDto commentDto = this.mapper.map(comment, CommentDto.class);
		
		return commentDto;
	}

	@Override
	public void deleteComment(int id) {
		
		Comment comment = this.commentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Comment", "id", id));

		this.commentRepository.delete(comment);
		
	}

	@Override
	public List<CommentDto> getCommentByUser(int uId) {
		
		User user = this.userRepository.findById(uId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", uId));
		
		List<Comment> commentList = this.commentRepository.findByUser(user);
	
		List<CommentDto> dtos = commentList.stream().map((com)-> this.mapper.map(com, CommentDto.class)).collect(Collectors.toList());
		
		return dtos;
	}
	
	

}
