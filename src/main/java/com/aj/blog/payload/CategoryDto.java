package com.aj.blog.payload;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CategoryDto {
	
	private int categoryId;
	
	
	private String categoryTitle;
	
	
	private String categoryDescription;

}
