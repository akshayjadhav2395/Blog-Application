package com.aj.blog.services;

import java.util.List;

import com.aj.blog.payload.PostDto;
import com.aj.blog.payload.PostResponse;

public interface PostService {

	public PostDto createPost(PostDto postDto, int uId, int categoryId);

	public PostDto updatePost(PostDto postDto, int postId);

	public PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy, String sortDir);

	public PostDto getPostByPostId(int postId);

	public void deletePost(int postId);

	public PostResponse getPostByUser(int uId, int pageNumber, int pageSize, String sortBy, String sortDir);

	public PostResponse getPostByCategory(int categoryId, int pageNumber, int pageSize, String sortBy, String sortDir);

	public List<PostDto> searchPost(String keyword);
}
