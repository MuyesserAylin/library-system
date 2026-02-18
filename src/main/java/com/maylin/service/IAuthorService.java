package com.maylin.service;

import java.util.List;

import com.maylin.dto.DtoAuthorRequest;
import com.maylin.dto.DtoAuthorResponse;
import com.maylin.dto.DtoAuthorUpdate;

import jakarta.validation.Valid;

public interface IAuthorService {
	
	public  DtoAuthorResponse saveAuthor(DtoAuthorRequest request);
	
	public List<DtoAuthorResponse> getAllAuthors();
	
	public DtoAuthorResponse getAuthorById(Long id);
	
	public void deleteAuthor(Long id);
	
	public DtoAuthorResponse updateAuthor(Long id,DtoAuthorUpdate updateAuthor);
	

}
