package com.aj.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.aj.blog.entities.Category;
import com.aj.blog.entities.Post;
import com.aj.blog.entities.User;
import com.aj.blog.exceptions.ResourceNotFoundException;
import com.aj.blog.payload.CategoryDto;
import com.aj.blog.payload.PostDto;
import com.aj.blog.payload.PostResponse;
import com.aj.blog.repositories.CategoryRepository;
import com.aj.blog.repositories.PostRepository;
import com.aj.blog.repositories.UserRepository;
import com.aj.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public PostDto createPost(PostDto postDto, int uId, int categoryId) {
		
		User user = this.userRepository.findById(uId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", uId));
		
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "categoryId", categoryId));
		
		Post post = this.mapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post savedPost = this.postRepository.save(post);
		
		PostDto dto = this.mapper.map(savedPost, PostDto.class);
		
		return dto;
	}

	@Override
	public PostDto updatePost(PostDto postDto, int postId) {
		
		Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
		
		post.setPostTittle(postDto.getPostTittle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
//		post.setCategory(postDto.getCategoryDto());
		
		Post updatedPost = this.postRepository.save(post);
		
		PostDto dto = this.mapper.map(updatedPost, PostDto.class);
		
		return dto;
	}

	@Override
	public PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy, String sortDir) {
		
		Sort sort=null;
		
		if(sortDir.equalsIgnoreCase("asc"))
		{
			sort=Sort.by(sortBy).ascending();
		}
		else {
			sort=Sort.by(sortBy).descending();
		}
		
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		
		Page<Post> pagePost = this.postRepository.findAll(pageable);
		
		List<Post> list = pagePost.getContent();
		
		List<PostDto> postDtos = list.stream().map((post)-> this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pageNumber);
		postResponse.setPageSize(pageSize);
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public PostDto getPostByPostId(int postId) {
		
		Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Posts", "postId", postId));
		
		PostDto postDto = this.mapper.map(post, PostDto.class);
		 
		return postDto;
	}

	@Override
	public void deletePost(int postId) {
	
		Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Posts", "postId", postId));
		
		this.postRepository.delete(post);
		
	}

	@Override
	public PostResponse getPostByUser(int uId, int pageNumber, int pageSize, String sortBy, String sortDir) {
		
		User user = this.userRepository.findById(uId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", uId));
		
		
		Sort sort=null;
		
		if(sortDir.equalsIgnoreCase("asc"))
		{
			sort=Sort.by(sortBy).ascending();
		}
		else {
			sort=Sort.by(sortBy).descending();
		}
		
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		
		Page<Post> pagePost = this.postRepository.findByUser(user, pageable);
		 
		List<Post> list = pagePost.getContent();
		
		List<PostDto> postDtos = list.stream().map((post)-> this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pageNumber);
		postResponse.setPageSize(pageSize);
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public PostResponse getPostByCategory(int categoryId, int pageNumber, int pageSize, String sortBy, String sortDir) {
		
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "categoryId", categoryId));
		
		
		Sort sort=null;
		
		if(sortDir.equalsIgnoreCase("asc"))
		{
			sort=Sort.by(sortBy).ascending();
		}
		else {
			sort=Sort.by(sortBy).descending();
		}
		
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		
		Page<Post> pagePost = this.postRepository.findByCategory(category, pageable);
		 
		List<Post> list = pagePost.getContent();
	
		
		List<PostDto> postDtos = list.stream().map((post)-> this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pageNumber);
		postResponse.setPageSize(pageSize);
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		 
		return postResponse;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		
		List<Post> list = this.postRepository.findByPostTittleContaining(keyword);
		
		List<PostDto> postDtos = list.stream().map((post)-> this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

}
