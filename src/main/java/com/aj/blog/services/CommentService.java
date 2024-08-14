package com.aj.blog.services;

import java.util.List;

import com.aj.blog.payload.CommentDto;

public interface CommentService {

	public CommentDto createComment(CommentDto commentDto, int postId, int uId);

	public List<CommentDto> getAllComments();

	public CommentDto getCommentById(int id);
	
	public List<CommentDto> getCommentByUser(int uId);

	public void deleteComment(int id);
	
}
