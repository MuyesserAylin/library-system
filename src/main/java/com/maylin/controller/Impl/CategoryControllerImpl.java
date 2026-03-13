package com.maylin.controller.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.maylin.dto.ApiResponse;
import com.maylin.dto.DtoCategoryRequest;
import com.maylin.dto.DtoCategoryResponse;
import com.maylin.dto.DtoCategoryShortResponse;
import com.maylin.dto.DtoCategoryUpdate;
import com.maylin.service.ICategoryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path="/rest/api/category")
@Validated
@RequiredArgsConstructor
public class CategoryControllerImpl extends RestBaseController implements ICategoryController{

	
	private final ICategoryService categoryService;
	
	@Override
    @PostMapping("/save")
    public ResponseEntity<ApiResponse<DtoCategoryResponse>> saveCategory(@Valid @RequestBody DtoCategoryRequest request, HttpServletRequest httpRequest) {
        return created(categoryService.saveCategory(request), httpRequest);
    }

    @Override
    @GetMapping("/list/{id}")
    public ResponseEntity<ApiResponse<DtoCategoryResponse>> getCategoryById(@PathVariable("id") @Min(1) Long id, HttpServletRequest httpRequest) {
        return ok(categoryService.getCategoryById(id), httpRequest);
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<DtoCategoryShortResponse>>> getAllCategories(HttpServletRequest httpRequest) {
        return ok(categoryService.getAllCategories(), httpRequest);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable("id") @Min(1) Long id, HttpServletRequest httpRequest) {
        categoryService.deleteCategory(id);
        return ok(null, httpRequest);
    }

    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<DtoCategoryShortResponse>> updateCategory(@PathVariable("id") @Min(1) Long id, @Valid @RequestBody DtoCategoryUpdate updateCategory, HttpServletRequest httpRequest) {
        return ok(categoryService.updateCategory(id, updateCategory), httpRequest);
    }
}




	