package com.maylin.controller;

import com.maylin.dto.DtoAuthorRequest;
import com.maylin.dto.DtoAuthorResponse;

import jakarta.validation.Valid;

public interface IAuthorController {
	
	public  DtoAuthorResponse saveAuthor( @Valid DtoAuthorRequest request);

}
