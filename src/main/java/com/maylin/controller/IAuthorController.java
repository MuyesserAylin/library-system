package com.maylin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.maylin.dto.ApiResponse;
import com.maylin.dto.DtoAuthorRequest;
import com.maylin.dto.DtoAuthorResponse;
import com.maylin.dto.DtoAuthorUpdate;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

public interface IAuthorController {
	
	ResponseEntity<ApiResponse<DtoAuthorResponse>> saveAuthor(@Valid DtoAuthorRequest request, HttpServletRequest httpRequest);
    ResponseEntity<ApiResponse<List<DtoAuthorResponse>>> getAllAuthors(HttpServletRequest httpRequest);
    ResponseEntity<ApiResponse<DtoAuthorResponse>> getAuthorById(@Min(1) Long id, HttpServletRequest httpRequest);
    ResponseEntity<ApiResponse<Void>> deleteAuthor(@Min(1) Long id, HttpServletRequest httpRequest);
    ResponseEntity<ApiResponse<DtoAuthorResponse>> updateAuthor(@Min(1) Long id, @Valid DtoAuthorUpdate updateAuthor, HttpServletRequest httpRequest);
}
