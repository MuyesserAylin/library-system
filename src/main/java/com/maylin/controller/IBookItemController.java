package com.maylin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.maylin.dto.ApiResponse;
import com.maylin.dto.DtoBookItemDetailResponse;
import com.maylin.dto.DtoBookItemRequest;
import com.maylin.dto.DtoBookItemResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

public interface IBookItemController {
	
	ResponseEntity<ApiResponse<DtoBookItemResponse>> saveBookitem(@Valid DtoBookItemRequest request, HttpServletRequest httpRequest);
    ResponseEntity<ApiResponse<DtoBookItemDetailResponse>> getBookItemById(@Min(1) Long id, HttpServletRequest httpRequest);
    ResponseEntity<ApiResponse<Void>> deleteBookItem(@Min(1) Long id, HttpServletRequest httpRequest);
    ResponseEntity<ApiResponse<List<DtoBookItemResponse>>> getBookItemByBookId(@Min(1) Long bookId, HttpServletRequest httpRequest);

}
