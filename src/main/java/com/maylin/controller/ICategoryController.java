package com.maylin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.maylin.dto.ApiResponse;
import com.maylin.dto.DtoCategoryRequest;
import com.maylin.dto.DtoCategoryResponse;
import com.maylin.dto.DtoCategoryShortResponse;
import com.maylin.dto.DtoCategoryUpdate;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

public interface ICategoryController {

	 ResponseEntity<ApiResponse<DtoCategoryResponse>> saveCategory(@Valid DtoCategoryRequest request, HttpServletRequest httpRequest);
	 ResponseEntity<ApiResponse<DtoCategoryResponse>> getCategoryById(@Min(1) Long id, HttpServletRequest httpRequest);
	 ResponseEntity<ApiResponse<List<DtoCategoryShortResponse>>> getAllCategories(HttpServletRequest httpRequest);
	 ResponseEntity<ApiResponse<Void>> deleteCategory(@Min(1) Long id, HttpServletRequest httpRequest);
	 ResponseEntity<ApiResponse<DtoCategoryShortResponse>> updateCategory(@Min(1) Long id, @Valid DtoCategoryUpdate updateCategory, HttpServletRequest httpRequest);

	
	
}
