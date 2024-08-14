package com.aj.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aj.blog.payload.ApiResponse;
import com.aj.blog.payload.CommentDto;
import com.aj.blog.services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@PostMapping("/user/{uId}/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable int postId, @PathVariable int uId)
	{
		CommentDto comment = this.commentService.createComment(commentDto, postId, uId);
		
		return new ResponseEntity<CommentDto> (comment, HttpStatus.CREATED);
	}
		
	@GetMapping("/comments")
	public ResponseEntity<List<CommentDto>> getAllComments()
	{
		List<CommentDto> list = this.commentService.getAllComments();
		
		return new ResponseEntity<List<CommentDto>> (list, HttpStatus.OK);
	}
	
	@GetMapping("/comments/{id}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable int id)
	{
		CommentDto comment = this.commentService.getCommentById(id);
		
		return new ResponseEntity<CommentDto> (comment, HttpStatus.OK);
	}
	
	@GetMapping("/user/{uId}/comments")
	public ResponseEntity<List<CommentDto>> getCommentByUser(@PathVariable int uId)
	{
		List<CommentDto> commentDtos = this.commentService.getCommentByUser(uId);
		
		return new ResponseEntity<List<CommentDto>> (commentDtos, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/comments/{id}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable int id)
	{
		this.commentService.deleteComment(id);
		
		return new ResponseEntity<ApiResponse> (new ApiResponse("Comment deleted successfully..!", true), HttpStatus.OK);
	}
}
