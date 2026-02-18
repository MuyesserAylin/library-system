package com.maylin.service;

import java.util.List;

import com.maylin.dto.DtoAuthorRequest;
import com.maylin.dto.DtoAuthorResponse;

import jakarta.validation.Valid;

public interface IAuthorService {
	
	public  DtoAuthorResponse saveAuthor(DtoAuthorRequest request);
	
	public List<DtoAuthorResponse> getAllAuthors();
	
	public DtoAuthorResponse getAuthorById(Long id);
	

}
