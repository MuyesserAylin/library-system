package com.maylin.service;

import java.util.List;

import com.maylin.dto.DtoBookListResponse;
import com.maylin.dto.DtoBookRequest;
import com.maylin.dto.DtoBookResponse;

import jakarta.validation.Valid;

public interface IBookService {
	
	public DtoBookResponse saveBook(DtoBookRequest request);
	
	public DtoBookResponse getBookById(Long id);
	
	public List<DtoBookListResponse> getAllBooks();
	
	public void deleteBook(Long id);
	

}
