package com.maylin.controller.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maylin.controller.IAuthorController;
import com.maylin.dto.DtoAuthorRequest;
import com.maylin.dto.DtoAuthorResponse;
import com.maylin.dto.DtoAuthorUpdate;
import com.maylin.mapper.IAuthorMapper;
import com.maylin.model.Author;
import com.maylin.service.IAuthorService;

import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping(path="/rest/api/author")
public class AuthorControllerImpl  implements IAuthorController{
	
	@Autowired
	private IAuthorService authorService;

	@Override
	@PostMapping(path="/save")
	public DtoAuthorResponse saveAuthor(@RequestBody DtoAuthorRequest request) {
		
		return authorService.saveAuthor(request);
	}

	
	@Override
	@GetMapping(path="/list")
	public List<DtoAuthorResponse> getAllAuthors() {
		return authorService.getAllAuthors();
	}


	@Override
	@GetMapping(path="/list/{id}")
	public DtoAuthorResponse getAuthorById(@PathVariable("id")Long id) {
		// TODO Auto-generated method stub
		return authorService.getAuthorById(id);
	}


	@Override
	@DeleteMapping(path="/delete({id}")
	public void deleteAuthor(@PathVariable("id")Long id) {
		authorService.deleteAuthor(id);
		
	}


	@Override
	@PutMapping(path="/update/{id}")
	public DtoAuthorResponse updateAuthor(@PathVariable("id")Long id, @RequestBody DtoAuthorUpdate updateAuthor) {
		return authorService.updateAuthor(id, updateAuthor);
	}
	
	
	
	
	
	
	
	

}
