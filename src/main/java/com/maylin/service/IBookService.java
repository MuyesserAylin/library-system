package com.maylin.service;

import java.util.List;

import com.maylin.dto.DtoBookCategoryUpdate;
import com.maylin.dto.DtoBookListResponse;
import com.maylin.dto.DtoBookRequest;
import com.maylin.dto.DtoBookResponse;
import com.maylin.dto.DtoBookUpdate;

import jakarta.validation.Valid;

public interface IBookService {
	
	public DtoBookResponse saveBook(DtoBookRequest request);
	
	public DtoBookResponse getBookById(Long id);
	
	public List<DtoBookListResponse> getAllBooks();
	
	public void deleteBook(Long id);
	
	public DtoBookResponse updateBook(Long id,DtoBookUpdate updateBook);
	
	public DtoBookResponse updateBookCategories(Long id,DtoBookCategoryUpdate updateCategory);
	
	public DtoBookResponse addBookCategory(Long id,Long categoryId);
	
	public DtoBookResponse removeBookCategory(Long id,Long categoryId);
	

}
