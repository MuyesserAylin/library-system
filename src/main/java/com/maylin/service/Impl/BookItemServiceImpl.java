package com.maylin.service.Impl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.maylin.dto.DtoBookItemRequest;
import com.maylin.dto.DtoBookItemResponse;
import com.maylin.dto.DtoBookShortResponse;
import com.maylin.enums.ErrorCode;
import com.maylin.exception.BaseException;
import com.maylin.mapper.IBookItemMapper;
import com.maylin.mapper.IBookMapper;
import com.maylin.model.Book;
import com.maylin.model.BookItem;
import com.maylin.repository.IBookItemRepository;
import com.maylin.repository.IBookRepository;
import com.maylin.service.IBookItemService;
import com.maylin.util.StringUtil;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class BookItemServiceImpl implements IBookItemService{
	
	private final IBookItemRepository bookitemRepository;
	private final IBookRepository bookRepository;
	private final IBookItemMapper bookitemMapper;
	private final IBookMapper bookMapper;

	@Override
	public DtoBookItemResponse saveBookitem(DtoBookItemRequest request) {
		
		boolean exists=bookitemRepository.existsByBarcode(StringUtil.formatBarcode(request.getBarcode()));
		if(exists) {
			throw new BaseException(ErrorCode.BOOKITEM_ALREADY_EXISTS,HttpStatus.CONFLICT);
		}
		
		Book book=findBookById(request.getBookId());
		BookItem bookitem=bookitemMapper.toEntity(request);
		bookitem.setBook(book);
		BookItem savedBookitem=bookitemRepository.save(bookitem);
		return buildBookItemResponse(savedBookitem);
		
	}
	
	private Book findBookById(Long bookId) {
		return bookRepository.findByIdWithAuthor(bookId)
				.orElseThrow(()->new BaseException(ErrorCode.BOOK_NOT_FOUND,HttpStatus.NOT_FOUND));
		
	}
	
	private DtoBookItemResponse buildBookItemResponse(BookItem bookitem) {
		
		DtoBookItemResponse response=bookitemMapper.toDtoBookItemResponse(bookitem);
		DtoBookShortResponse dtoBook=bookMapper.toBookShortResponse(bookitem.getBook());
		response.setBook(dtoBook);
		return response;
		
	}
	
	

}
