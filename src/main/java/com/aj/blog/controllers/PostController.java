package com.aj.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aj.blog.payload.ApiResponse;
import com.aj.blog.payload.PostDto;
import com.aj.blog.payload.PostResponse;
import com.aj.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;

	@PostMapping("/user/{uId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable int uId,
			@PathVariable int categoryId)
	{
		PostDto post = this.postService.createPost(postDto, uId, categoryId);
		
		return new ResponseEntity<PostDto> (post, HttpStatus.CREATED);
	}
	
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto dto, @PathVariable int postId)
	{
		PostDto updatePost = this.postService.updatePost(dto, postId);
		
		return new ResponseEntity<PostDto> (updatePost, HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir)
	{
		PostResponse postResponse = this.postService.getAllPosts(pageNumber,pageSize, sortBy, sortDir);
		
		return new ResponseEntity<PostResponse> (postResponse, HttpStatus.OK);
	}
	
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostByPostId(@PathVariable int postId)
	{
		PostDto post = this.postService.getPostByPostId(postId);
		
		return new ResponseEntity<PostDto> (post, HttpStatus.OK);
	}
	
	@GetMapping("/user/{uId}/posts")
	public ResponseEntity<PostResponse> getPostByUser(@PathVariable int uId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir)
	{
		PostResponse postByUser = this.postService.getPostByUser(uId, pageNumber, pageSize, sortBy, sortDir);
		
		return new ResponseEntity<PostResponse> (postByUser, HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<PostResponse> getPostByCategory(@PathVariable int categoryId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir)
	{
		PostResponse postByCategory = this.postService.getPostByCategory(categoryId, pageNumber, pageSize, sortBy, sortDir);
		
		return new ResponseEntity<PostResponse> (postByCategory, HttpStatus.OK);
	}
	
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable int postId)
	{
		this.postService.deletePost(postId);
		
		return new ResponseEntity<ApiResponse> (new ApiResponse("Post deleted successfully..!", true), HttpStatus.OK);
	}
	
	@GetMapping("/post/keyword/{keyword}")
	public ResponseEntity<List<PostDto>> searchByPostTittle(@PathVariable String keyword)
	{
		List<PostDto> searchPost = this.postService.searchPost(keyword);
		
		return new ResponseEntity<List<PostDto>> (searchPost, HttpStatus.OK);
	}
	
	
}

