package com.aj.blog.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PostDto {

	private int postId; 
	
	@NotEmpty(message = "Title Should be min of 4 characters..!")
	@Size(min = 4, max = 20)
	private String postTittle;

	@NotEmpty(message = "Content Should be min of 2000 characters..!")
	@Size(min = 2000, max = 20000)
	private String content;

	private String imageName;

	private Date addedDate;

	private CategoryDto category;

	private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();

}
