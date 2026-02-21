package com.maylin.controller;

import java.util.List;

import com.maylin.dto.DtoAuthorRequest;
import com.maylin.dto.DtoAuthorResponse;
import com.maylin.dto.DtoAuthorUpdate;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

public interface IAuthorController {
	
	public  DtoAuthorResponse saveAuthor( @Valid DtoAuthorRequest request);
	
	public List<DtoAuthorResponse> getAllAuthors();
	
	public DtoAuthorResponse getAuthorById(@Min(value=1,message="Category ID must be at least 1.")Long id);
	
	public void deleteAuthor(@Min(value=1,message="Category ID must be at least 1.")Long id);
	
	public DtoAuthorResponse updateAuthor(@Min(value=1,message="Category ID must be at least 1.")Long id,@Valid DtoAuthorUpdate updateAuthor);
}
