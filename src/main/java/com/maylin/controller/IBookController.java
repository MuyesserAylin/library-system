package com.maylin.controller;

import com.maylin.dto.DtoBookRequest;
import com.maylin.dto.DtoBookResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

public interface IBookController {
	
	public DtoBookResponse saveBook(@Valid DtoBookRequest request);
	
	public DtoBookResponse getBookById(@Min(value=1,message="Book id can be 1 least.")Long id);

}
