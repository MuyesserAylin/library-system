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

import com.maylin.controller.IAuthorController;
import com.maylin.dto.ApiResponse;
import com.maylin.dto.DtoAuthorRequest;
import com.maylin.dto.DtoAuthorResponse;
import com.maylin.dto.DtoAuthorUpdate;
import com.maylin.mapper.IAuthorMapper;
import com.maylin.model.Author;
import com.maylin.service.IAuthorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping(path="/rest/api/author")
@RequiredArgsConstructor
@Validated
public class AuthorControllerImpl extends RestBaseController implements IAuthorController{
	
	private final IAuthorService authorService;

	@Override
	@PostMapping("/save")
	public ResponseEntity<ApiResponse<DtoAuthorResponse>> saveAuthor(@Valid @RequestBody DtoAuthorRequest request, HttpServletRequest httpRequest) {
	    return created(authorService.saveAuthor(request), httpRequest);
	}

	@Override
	@GetMapping("/list")
	public ResponseEntity<ApiResponse<List<DtoAuthorResponse>>> getAllAuthors(HttpServletRequest httpRequest) {
	    return ok(authorService.getAllAuthors(), httpRequest);
	}

	@Override
	@GetMapping("/list/{id}")
	public ResponseEntity<ApiResponse<DtoAuthorResponse>> getAuthorById(@PathVariable("id") @Min(1) Long id, HttpServletRequest httpRequest) {
	    return ok(authorService.getAuthorById(id), httpRequest);
	}

	@Override
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteAuthor(@PathVariable("id") @Min(1) Long id, HttpServletRequest httpRequest) {
	    authorService.deleteAuthor(id);
	    return ok(null, httpRequest);
	}

	@Override
	@PutMapping("/update/{id}")
	public ResponseEntity<ApiResponse<DtoAuthorResponse>> updateAuthor(@PathVariable("id") @Min(1) Long id, @Valid @RequestBody DtoAuthorUpdate updateAuthor, HttpServletRequest httpRequest) {
	    return ok(authorService.updateAuthor(id, updateAuthor), httpRequest);
	}
	
	
	
	

}
