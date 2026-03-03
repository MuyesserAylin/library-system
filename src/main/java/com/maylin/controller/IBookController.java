package com.maylin.controller;

import java.util.List;

import com.maylin.dto.DtoBookCategoryUpdate;
import com.maylin.dto.DtoBookListResponse;
import com.maylin.dto.DtoBookRequest;
import com.maylin.dto.DtoBookResponse;
import com.maylin.dto.DtoBookShortResponse;
import com.maylin.dto.DtoBookUpdate;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

public interface IBookController {
	
	public DtoBookResponse saveBook(@Valid DtoBookRequest request);
	
	public DtoBookResponse getBookById(@Min(value=1,message="Book id can be 1 least.")Long id);
	
	public List<DtoBookListResponse> getAllBooks();
	
	public void deleteBook(@Min(value=1,message="Book id can be 1 least.")Long id);
	
	public DtoBookResponse updateBook(@Min(value=1,message="Book id can be 1 least.")Long id,@Valid DtoBookUpdate updateBook);
	
	public DtoBookResponse updateBookCategories(@Min(value=1,message="Book id can be 1 least.")Long id,@Valid DtoBookCategoryUpdate updateCategory);
	
	public DtoBookResponse addBookCategory(@Min(value=1,message="Book id can be 1 least.")Long id,@Min(value=1,message="Category id can be 1 least.")Long categoryId);
	
	public DtoBookResponse removeBookCategory(@Min(value=1,message="Book id can be 1 least.")Long id,@Min(value=1,message="Category id can be 1 least.")Long categoryId);
	
	
}
