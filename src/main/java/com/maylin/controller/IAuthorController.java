package com.maylin.controller;

import java.util.List;

import com.maylin.dto.DtoAuthorRequest;
import com.maylin.dto.DtoAuthorResponse;
import com.maylin.dto.DtoAuthorUpdate;

import jakarta.validation.Valid;

public interface IAuthorController {
	
	public  DtoAuthorResponse saveAuthor( @Valid DtoAuthorRequest request);
	
	public List<DtoAuthorResponse> getAllAuthors();
	
	public DtoAuthorResponse getAuthorById(Long id);
	
	public void deleteAuthor(Long id);
	
	public DtoAuthorResponse updateAuthor(Long id,DtoAuthorUpdate updateAuthor);
}
