package com.maylin.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maylin.dto.DtoBookShortResponse;
import com.maylin.dto.DtoCategoryRequest;
import com.maylin.dto.DtoCategoryResponse;
import com.maylin.dto.DtoCategoryShortResponse;
import com.maylin.mapper.ICategoryMapper;
import com.maylin.model.Book;
import com.maylin.model.Category;
import com.maylin.repository.ICategoryRepository;
import com.maylin.service.ICategoryService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService{

	
	private final ICategoryRepository categoryRepository;
	private final ICategoryMapper categoryMapper;
	
	@Override
	public DtoCategoryResponse saveCategory(DtoCategoryRequest request) {
		
		String name=request.getName().trim();
		Boolean exists=categoryRepository.existsByNameIgnoreCase(name);
		if(exists) {
			throw new RuntimeException("This category allready exists in system.");
			//TODO:Bu adda category var hatası fırlat.
		}
		Category newCategory=categoryMapper.toEntity(request);
		Category dbCategory=categoryRepository.save(newCategory);
	
		return categoryMapper.toCategoryResponse(dbCategory);
	}

	@Override
	public DtoCategoryResponse getCategoryById(Long id) {
		
	 Optional<Category> optional=categoryRepository.findCategoryWithBooks(id);
	 
	if(optional.isPresent()) {
		
		Category dbCategory=optional.get();
		return categoryMapper.toCategoryResponse(dbCategory);
		
	}else {
		return null;
		//TODO:exception fılratıcak aranılan categoru dbde yok.sımdılık null gıtsın
	}
	 
	}

	@Override
	public List<DtoCategoryShortResponse> getAllCategories() {
		List<Category> allCategories=categoryRepository.findByOrderByNameAsc();
		return categoryMapper.toCategoryShortResponseList(allCategories);	
		
	}

	@Override
	public void deleteCategory(Long id) {
		Category category=categoryRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Not found category."));//burayada ozel exception fırlatcam
		
		List<Book> books=category.getBooks();
		if(books!=null && !books.isEmpty()) {
			
			//TODO: exception fırlat bu categoryının kıtapları var once o kıtapları hallet sonra sıl dıye
			throw new RuntimeException("Category include books.Not delete!");
		}
		
		categoryRepository.delete(category);
	}

	@Override
	public DtoCategoryShortResponse updateCategory(Long id, DtoCategoryRequest updateCategory) {
		Category category=categoryRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Not Found category."));//burayada ozel exceptıon gelıcek.verıtababında bu idlı yok dıye
		
		String newName=updateCategory.getName().trim().toLowerCase();
		
		if(categoryRepository.existsByNameIgnoreCase(newName) && !category.getName().trim().equalsIgnoreCase(newName)) {
              //Eğer bu isim veritabanında varsa AMA benim şu an elimde tuttuğum nesnenin eski ismiyle aynı değilse, o zaman bu isim başka birine aittir, hata ver!
				throw new RuntimeException("Category already esists by this name.");
				//TODO:ozel exception fırlat bu ada sahıp var dıye
			
		}
		
		
		category.setName(updateCategory.getName());
		Category dbCategory=categoryRepository.save(category);
		return categoryMapper.toCategoryShortResponse(dbCategory);
	}

}
