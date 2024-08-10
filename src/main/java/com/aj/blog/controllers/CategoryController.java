package com.aj.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aj.blog.payload.ApiResponse;
import com.aj.blog.payload.CategoryDto;
import com.aj.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto category = this.categoryService.createCategory(categoryDto);
		
		return new ResponseEntity<CategoryDto> (category, HttpStatus.CREATED);
	}
	
	@PutMapping("/category/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable int categoryId)
	{
		CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, categoryId);
		
		return new ResponseEntity<CategoryDto> (updateCategory, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories()
	{
		List<CategoryDto> allCategories = this.categoryService.getAllCategories();
		
		return new ResponseEntity<List<CategoryDto>> (allCategories, HttpStatus.OK);
	}

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryByCategoryId(@PathVariable int categoryId)
	{
		CategoryDto categoryByCategoryId = this.categoryService.getCategoryByCategoryId(categoryId);
		
		return new ResponseEntity<CategoryDto> (categoryByCategoryId, HttpStatus.OK);
	}
	
	@DeleteMapping("/category/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int categoryId)
	{
		this.categoryService.deleteCategory(categoryId);
		
		return new ResponseEntity<ApiResponse> (new ApiResponse("Category Deleted Successfully..!", true), HttpStatus.OK);
	}
}
