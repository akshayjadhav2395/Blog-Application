package com.aj.blog.services;

import java.util.List;

import com.aj.blog.payload.CategoryDto;

public interface CategoryService {

	public CategoryDto createCategory(CategoryDto categoryDto);

	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);

	public List<CategoryDto> getAllCategories();

	public CategoryDto getCategoryByCategoryId(int categoryId);

	public void deleteCategory(int categoryId);

}
