package com.aj.blog.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class JwtAuthRequest {

	private String username;
    private String password;
	
}
