package com.maylin.controller.Impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maylin.controller.IBookItemController;
import com.maylin.dto.ApiResponse;
import com.maylin.dto.DtoBookItemDetailResponse;
import com.maylin.dto.DtoBookItemRequest;
import com.maylin.dto.DtoBookItemResponse;
import com.maylin.service.IBookItemService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/rest/api/bookitem")
@RequiredArgsConstructor
@Validated
public class BookItemControllerImpl extends RestBaseController implements IBookItemController{
	
	private final IBookItemService bookitemService;

	@Override
    @PostMapping("/save")
    public ResponseEntity<ApiResponse<DtoBookItemResponse>> saveBookitem(@Valid @RequestBody DtoBookItemRequest request, HttpServletRequest httpRequest) {
        return created(bookitemService.saveBookitem(request), httpRequest);
    }

    @Override
    @GetMapping("/list/{id}")
    public ResponseEntity<ApiResponse<DtoBookItemDetailResponse>> getBookItemById(@PathVariable("id") @Min(1) Long id, HttpServletRequest httpRequest) {
        return ok(bookitemService.getBookItemById(id), httpRequest);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBookItem(@PathVariable("id") @Min(1) Long id, HttpServletRequest httpRequest) {
        bookitemService.deleteBookItem(id);
        return ok(null, httpRequest);
    }
    
    @Override
    @GetMapping("/list/book/{bookId}")
    public ResponseEntity<ApiResponse<List<DtoBookItemResponse>>> getBookItemByBookId(@PathVariable("bookId") @Min(1) Long bookId, HttpServletRequest httpRequest) {
        return ok(bookitemService.getBookItemByBookId(bookId), httpRequest);
    }

}
