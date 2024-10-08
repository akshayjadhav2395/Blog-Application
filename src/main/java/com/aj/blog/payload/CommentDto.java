package com.aj.blog.payload;

import com.aj.blog.entities.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CommentDto {

	private int id;
	
	private String content;
	
//	private PostDto post;
	
	private UserDto user;
}
