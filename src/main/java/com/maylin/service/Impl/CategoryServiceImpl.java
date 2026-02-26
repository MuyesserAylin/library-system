package com.maylin.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.maylin.dto.DtoBookShortResponse;
import com.maylin.dto.DtoCategoryRequest;
import com.maylin.dto.DtoCategoryResponse;
import com.maylin.dto.DtoCategoryShortResponse;
import com.maylin.dto.DtoCategoryUpdate;
import com.maylin.enums.ErrorCode;
import com.maylin.exception.BaseException;
import com.maylin.mapper.IBookMapper;
import com.maylin.mapper.ICategoryMapper;
import com.maylin.model.Book;
import com.maylin.model.Category;
import com.maylin.repository.ICategoryRepository;
import com.maylin.service.ICategoryService;
import com.maylin.util.StringUtil;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService{

	
	private final ICategoryRepository categoryRepository;
	private final ICategoryMapper categoryMapper;
	private final IBookMapper bookMapper;
	
	@Override
	public DtoCategoryResponse saveCategory(DtoCategoryRequest request) {
		
		String cleanedName=StringUtil.formatName(request.getName());
		Boolean exists=categoryRepository.existsByNameIgnoreCase(cleanedName);
		if(exists) {
			throw new BaseException(ErrorCode.CATEGORY_ALREADY_EXISTS,HttpStatus.CONFLICT);
		}
		
		request.setName(cleanedName);
		Category newCategory=categoryMapper.toEntity(request);
		Category dbCategory=categoryRepository.save(newCategory);
	
		return buildCategoryResponse(dbCategory);
	}

	@Override
	public DtoCategoryResponse getCategoryById(Long id) {
		
	 Category category=findCategoryById(id);
	 
		return buildCategoryResponse(category);
		
	}

	@Override
	public List<DtoCategoryShortResponse> getAllCategories() {
		List<Category> allCategories=categoryRepository.findByOrderByNameAsc();
		return categoryMapper.toCategoryShortResponseList(allCategories);	
		
	}

	@Override
	public void deleteCategory(Long id) {
		Category category=findCategoryById(id);
		List<Book> books=category.getBooks();
		if(books!=null && !books.isEmpty()) {
			throw new BaseException(ErrorCode.CATEGORY_HAS_BOOKS,HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		categoryRepository.delete(category);
	}

	@Override
	public DtoCategoryShortResponse updateCategory(Long id, DtoCategoryUpdate updateCategory) {
		Category category=findCategoryById(id);
		String name=category.getName();
		
		if(isNotBlank(updateCategory.getName())) {
			name=StringUtil.formatName(updateCategory.getName());
		}
		
		if(categoryRepository.existsByNameIgnoreCase(name) && !category.getName().equals(name)) {
             throw new BaseException(ErrorCode.CATEGORY_ALREADY_EXISTS,HttpStatus.CONFLICT);
			
		}
		
		
		category.setName(name);
		Category dbCategory=categoryRepository.save(category);
		return categoryMapper.toCategoryShortResponse(dbCategory);
	}

	private Category findCategoryById(Long id) {
		
		return categoryRepository.findCategoryWithBooks(id)
				.orElseThrow(()->new BaseException(ErrorCode.CATEGORY_NOT_FOUND,HttpStatus.NOT_FOUND));
}
	
	private DtoCategoryResponse buildCategoryResponse(Category category) {
		
		DtoCategoryResponse response=categoryMapper.toDtoCategoryResponse(category);
		response.setBooks(bookMapper.toBookShortList(category.getBooks()));
		return response;
		
		
	}
	
	private Boolean isNotBlank(String value) {
		return value!=null && !value.isBlank();
	}
}
