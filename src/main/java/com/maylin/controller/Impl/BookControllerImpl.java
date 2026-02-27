package com.maylin.controller.Impl;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.maylin.controller.IBookController;
import com.maylin.dto.DtoBookRequest;
import com.maylin.dto.DtoBookResponse;
import com.maylin.service.IBookService;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/rest/api/book")
@RequiredArgsConstructor
@Validated
public class BookControllerImpl implements IBookController {
	
	private final IBookService bookService;

	@Override
	@PostMapping("/save")
	public DtoBookResponse saveBook(@RequestBody DtoBookRequest request) {
		return bookService.saveBook(request);
	}

	@Override
	@GetMapping("/list/{id}")
	public DtoBookResponse getBookById(@PathVariable("id") Long id) {
		// TODO Auto-generated method stub
		return bookService.getBookById(id);
	}

	
	

}
