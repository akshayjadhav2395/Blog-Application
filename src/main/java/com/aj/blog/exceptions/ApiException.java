package com.aj.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiException extends RuntimeException{

	private String resourceName;
	private String fieldName;
	private String fieldValue;
	
	public ApiException(String message) {
		super(message);

	}

	public ApiException() {
		super();

	}
}
