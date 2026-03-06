package com.maylin.controller;

import com.maylin.dto.DtoBookItemDetailResponse;
import com.maylin.dto.DtoBookItemRequest;
import com.maylin.dto.DtoBookItemResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

public interface IBookItemController {
	
	public DtoBookItemResponse saveBookitem(@Valid DtoBookItemRequest request);
	
	public DtoBookItemDetailResponse getBookItemById(@Min(value=1,message="BookItem id can be 1 least.")Long id);
	
	public void deleteBookItem(@Min(value=1,message="BookItem id can be 1 least.")Long id);

}
