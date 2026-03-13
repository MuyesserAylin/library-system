package com.maylin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.maylin.dto.ApiResponse;
import com.maylin.dto.DtoBookCategoryUpdate;
import com.maylin.dto.DtoBookListResponse;
import com.maylin.dto.DtoBookRequest;
import com.maylin.dto.DtoBookResponse;
import com.maylin.dto.DtoBookShortResponse;
import com.maylin.dto.DtoBookUpdate;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

public interface IBookController {
	
	ResponseEntity<ApiResponse<DtoBookResponse>> saveBook(@Valid DtoBookRequest request, HttpServletRequest httpRequest);
    ResponseEntity<ApiResponse<DtoBookResponse>> getBookById(@Min(1) Long id, HttpServletRequest httpRequest);
    ResponseEntity<ApiResponse<List<DtoBookListResponse>>> getAllBooks(HttpServletRequest httpRequest);
    ResponseEntity<ApiResponse<Void>> deleteBook(@Min(1) Long id, HttpServletRequest httpRequest);
    ResponseEntity<ApiResponse<DtoBookResponse>> updateBook(@Min(1) Long id, @Valid DtoBookUpdate updateBook, HttpServletRequest httpRequest);
    ResponseEntity<ApiResponse<DtoBookResponse>> updateBookCategories(@Min(1) Long id, @Valid DtoBookCategoryUpdate updateCategory, HttpServletRequest httpRequest);
    ResponseEntity<ApiResponse<DtoBookResponse>> addBookCategory(@Min(1) Long id, @Min(1) Long categoryId, HttpServletRequest httpRequest);
    ResponseEntity<ApiResponse<DtoBookResponse>> removeBookCategory(@Min(1) Long id, @Min(1) Long categoryId, HttpServletRequest httpRequest);
	
}
