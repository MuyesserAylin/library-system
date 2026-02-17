package com.maylin.service;

import com.maylin.dto.DtoAuthorRequest;
import com.maylin.dto.DtoAuthorResponse;

import jakarta.validation.Valid;

public interface IAuthorService {
	
	public  DtoAuthorResponse saveAuthor(DtoAuthorRequest request);

}
