package com.maylin.controller.Impl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.maylin.controller.IBookController;
import com.maylin.dto.DtoBookRequest;
import com.maylin.dto.DtoBookResponse;
import com.maylin.service.IBookService;

import lombok.RequiredArgsConstructor;
@RestController("/rest/api/book")
@RequiredArgsConstructor
public class BookControllerImpl implements IBookController {
	
	private final IBookService bookService;

	@Override
	@PostMapping("/save")
	public DtoBookResponse saveBook(@RequestBody DtoBookRequest request) {
		return bookService.saveBook(request);
	}
	
	

}
