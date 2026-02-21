package com.maylin.controller;

import java.util.List;

import com.maylin.dto.DtoCategoryRequest;
import com.maylin.dto.DtoCategoryResponse;
import com.maylin.dto.DtoCategoryShortResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

public interface ICategoryController {

	public DtoCategoryResponse saveCategory(@Valid DtoCategoryRequest request);
	
	public DtoCategoryResponse getCategoryById(@Min(value=1,message="Category ID must be at least 1.")Long id);
	
	public List<DtoCategoryShortResponse> getAllCategories();
	
	public void deleteCategory(@Min(value=1,message="Category ID must be at least 1.")Long id);
	
	public DtoCategoryShortResponse updateCategory(@Min(value=1, message="Category ID must be at least 1.")Long id, @Valid DtoCategoryRequest updateCategory);
	
}
