package com.maylin.service;

import com.maylin.dto.DtoBookRequest;
import com.maylin.dto.DtoBookResponse;

import jakarta.validation.Valid;

public interface IBookService {
	
	public DtoBookResponse saveBook(DtoBookRequest request);

}
