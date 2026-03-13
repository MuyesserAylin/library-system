package com.maylin.controller.Impl;

import java.util.List;

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
import com.maylin.controller.IBookController;
import com.maylin.dto.ApiResponse;
import com.maylin.dto.DtoBookCategoryUpdate;
import com.maylin.dto.DtoBookListResponse;
import com.maylin.dto.DtoBookRequest;
import com.maylin.dto.DtoBookResponse;
import com.maylin.dto.DtoBookShortResponse;
import com.maylin.dto.DtoBookUpdate;
import com.maylin.service.IBookService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rest/api/book")
@RequiredArgsConstructor
@Validated
public class BookControllerImpl extends RestBaseController implements IBookController {
	
	private final IBookService bookService;

	@Override
    @PostMapping("/save")
    public ResponseEntity<ApiResponse<DtoBookResponse>> saveBook(@Valid @RequestBody DtoBookRequest request, HttpServletRequest httpRequest) {
        return created(bookService.saveBook(request), httpRequest);
    }

    @Override
    @GetMapping("/list/{id}")
    public ResponseEntity<ApiResponse<DtoBookResponse>> getBookById(@PathVariable("id") @Min(1) Long id, HttpServletRequest httpRequest) {
        return ok(bookService.getBookById(id), httpRequest);
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<DtoBookListResponse>>> getAllBooks(HttpServletRequest httpRequest) {
        return ok(bookService.getAllBooks(), httpRequest);
    }
    
    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBook(@PathVariable("id") @Min(1) Long id, HttpServletRequest httpRequest) {
        bookService.deleteBook(id);
        return ok(null, httpRequest);
    }

    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<DtoBookResponse>> updateBook(@PathVariable("id") @Min(1) Long id, @Valid @RequestBody DtoBookUpdate updateBook, HttpServletRequest httpRequest) {
        return ok(bookService.updateBook(id, updateBook), httpRequest);
    }

    @Override
    @PutMapping("/update/{id}/categories")
    public ResponseEntity<ApiResponse<DtoBookResponse>> updateBookCategories(@PathVariable("id") @Min(1) Long id, @Valid @RequestBody DtoBookCategoryUpdate updateCategory, HttpServletRequest httpRequest) {
        return ok(bookService.updateBookCategories(id, updateCategory), httpRequest);
    }
    
    @Override
    @PostMapping("/{id}/categories/{categoryId}")
    public ResponseEntity<ApiResponse<DtoBookResponse>> addBookCategory(@PathVariable("id") @Min(1) Long id, @PathVariable("categoryId") @Min(1) Long categoryId, HttpServletRequest httpRequest) {
        return ok(bookService.addBookCategory(id, categoryId), httpRequest);
    }

    @Override
    @DeleteMapping("/{id}/categories/{categoryId}")
    public ResponseEntity<ApiResponse<DtoBookResponse>> removeBookCategory(@PathVariable("id") @Min(1) Long id, @PathVariable("categoryId") @Min(1) Long categoryId, HttpServletRequest httpRequest) {
        return ok(bookService.removeBookCategory(id, categoryId), httpRequest);
    }
}
