package com.aj.blog.payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CategoryDto {
	
	private int categoryId;
	
	@NotEmpty(message = "Title Should be min of 4 characters..!")
	@Size(min = 4, max = 20)
	private String categoryTitle;
	
	@NotEmpty(message = "Description Should not be empty..!")
	private String categoryDescription;

}
