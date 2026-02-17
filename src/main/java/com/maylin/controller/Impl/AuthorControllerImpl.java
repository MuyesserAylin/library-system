package com.maylin.controller.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maylin.controller.IAuthorController;
import com.maylin.dto.DtoAuthorRequest;
import com.maylin.dto.DtoAuthorResponse;
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
	
	
	
	

}
