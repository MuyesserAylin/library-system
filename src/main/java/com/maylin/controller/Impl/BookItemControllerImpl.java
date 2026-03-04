package com.maylin.controller.Impl;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maylin.controller.IBookItemController;
import com.maylin.dto.DtoBookItemRequest;
import com.maylin.dto.DtoBookItemResponse;
import com.maylin.service.IBookItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/rest/api/bookitem")
@RequiredArgsConstructor
@Validated
public class BookItemControllerImpl implements IBookItemController{
	
	private final IBookItemService bookitemService;

	@Override
	@PostMapping("/save")
	public DtoBookItemResponse saveBookitem(@RequestBody DtoBookItemRequest request) {
		return bookitemService.saveBookitem(request);
	}
	
	

}
