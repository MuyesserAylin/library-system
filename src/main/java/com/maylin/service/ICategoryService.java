package com.maylin.service;

import java.util.List;

import com.maylin.dto.DtoCategoryRequest;
import com.maylin.dto.DtoCategoryResponse;
import com.maylin.dto.DtoCategoryShortResponse;

public interface ICategoryService {
	
	public DtoCategoryResponse saveCategory(DtoCategoryRequest request);
	
	public DtoCategoryResponse getCategoryById(Long id);
	
	public List<DtoCategoryShortResponse> getAllCategories();
	
	public void deleteCategory(Long id);
	
	public DtoCategoryShortResponse updateCategory(Long id,DtoCategoryRequest updateCategory);

}
