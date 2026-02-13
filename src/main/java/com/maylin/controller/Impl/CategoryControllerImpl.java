package com.maylin.controller.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.maylin.controller.ICategoryController;
import com.maylin.dto.DtoCategoryRequest;
import com.maylin.dto.DtoCategoryResponse;
import com.maylin.dto.DtoCategoryShortResponse;
import com.maylin.service.ICategoryService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping(path="/rest/api/category")
@Validated
public class CategoryControllerImpl implements ICategoryController{

	@Autowired
	private ICategoryService categoryService;
	
	@Override
	@PostMapping(path="/save")
	public DtoCategoryResponse saveCategory(@RequestBody DtoCategoryRequest request) {
		
		return categoryService.saveCategory(request);
		
	}

	@Override
	@GetMapping(path="/list/{id}")
	public DtoCategoryResponse getCategoryById(@PathVariable("id") Long id) {
		
		//TODO:id negatif olursa exception fırlatıcak
		return categoryService.getCategoryById(id);
		
	}

	@Override
	@GetMapping(path="/list")
	public List<DtoCategoryShortResponse> getAllCategories() {
		
		return categoryService.getAllCategories();
	}

	@Override
	@DeleteMapping(path="/delete/{id}")
	public void deleteCategory(@PathVariable("id") Long id) {
		categoryService.deleteCategory(id);
	}
	
	@Override
	@PutMapping(path="/update/{id}")
	public DtoCategoryShortResponse updateCategory(@PathVariable("id") Long id,@RequestBody DtoCategoryRequest updateCategory) {
		
		//TODO: updateCategory ıcındekı valıdayonlar uymazsa exceptiom
		//TODO: Kullanıcı hıc verı gondermezzse  HttpMessageNotReadableException
		return categoryService.updateCategory(id,updateCategory);
		
	}
	
	
	
	
	



	}
