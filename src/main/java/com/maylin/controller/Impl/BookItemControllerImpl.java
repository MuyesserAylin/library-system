package com.maylin.controller.Impl;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maylin.controller.IBookItemController;
import com.maylin.dto.DtoBookItemDetailResponse;
import com.maylin.dto.DtoBookItemRequest;
import com.maylin.dto.DtoBookItemResponse;
import com.maylin.service.IBookItemService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/rest/api/bookitem")
@RequiredArgsConstructor
@Validated
public class BookItemControllerImpl implements IBookItemController{
	
	private final IBookItemService bookitemService;

	@Override
	@PostMapping("/save")
	public DtoBookItemResponse saveBookitem(@Valid @RequestBody DtoBookItemRequest request) {
		return bookitemService.saveBookitem(request);
	}

	@Override
	@GetMapping("/list/{id}")
	public DtoBookItemDetailResponse getBookItemById(@PathVariable("id")@Min(1)Long id) {
		return bookitemService.getBookItemById(id);
	}

	@Override
	@DeleteMapping("/delete/{id}")
	public void deleteBookItem(@PathVariable("id") @Min(1)Long id) {
       bookitemService.deleteBookItem(id);
	}

	@Override
	@GetMapping("/list/book/{bookId}")
	public List<DtoBookItemResponse> getBookItemByBookId(@PathVariable("bookId")@Min(1)Long bookId) {
		return bookitemService.getBookItemByBookId(bookId);
	}
	
	

}
