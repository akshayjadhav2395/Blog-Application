package com.aj.blog.payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDto {

	private int uId;
	
	@Column(name = "userName")
	@NotEmpty(message = "Name should be min of 4 Charachers..!")
	@Size(min = 4, max=20)
	private String name;
	
	@Email(message = "Please enter valid email address..!")
	private String email;
	
	@NotEmpty(message = "Password should be min of 4 and max of 10 Charachers..!")
	@Size(min = 4, max = 10)
	private String password;
	
	@NotEmpty(message = "About should not be empty..!")
	private String about;
	
	
}
