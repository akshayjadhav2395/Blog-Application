package com.aj.blog.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aj.blog.entities.Category;
import com.aj.blog.exceptions.ResourceNotFoundException;
import com.aj.blog.payload.CategoryDto;
import com.aj.blog.repositories.CategoryRepository;
import com.aj.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category category = this.mapper.map(categoryDto, Category.class);
		
		Category savedCategory = this.categoryRepository.save(category);
		
		CategoryDto dto = this.mapper.map(savedCategory, CategoryDto.class);
		
		return dto;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
		
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "categoryId", categoryId));
		
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedCategory = this.categoryRepository.save(category);
		
		CategoryDto dto = this.mapper.map(updatedCategory, CategoryDto.class);
		
		return dto;
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		
		List<Category> list = this.categoryRepository.findAll();
		
		List<CategoryDto> categoryDtos = list.stream().map((category)-> this.mapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		
		return categoryDtos;
	}

	@Override
	public CategoryDto getCategoryByCategoryId(int categoryId) {
		
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "categoryId", categoryId));
		
		CategoryDto categoryDto = this.mapper.map(category, CategoryDto.class);
		
		return categoryDto;
	}

	@Override
	public void deleteCategory(int categoryId) {
		
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "categoryId", categoryId));
	
		this.categoryRepository.delete(category);
	
	}

}
