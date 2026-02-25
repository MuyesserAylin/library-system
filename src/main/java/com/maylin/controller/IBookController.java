package com.maylin.controller;

import com.maylin.dto.DtoBookRequest;
import com.maylin.dto.DtoBookResponse;

import jakarta.validation.Valid;

public interface IBookController {
	
	public DtoBookResponse saveBook(@Valid DtoBookRequest request);

}
