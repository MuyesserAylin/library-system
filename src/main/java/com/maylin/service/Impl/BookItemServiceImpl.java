package com.maylin.service.Impl;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.maylin.dto.DtoBookItemDetailResponse;
import com.maylin.dto.DtoBookItemRequest;
import com.maylin.dto.DtoBookItemResponse;
import com.maylin.dto.DtoBookShortResponse;
import com.maylin.dto.DtoLoanForBookItem;
import com.maylin.enums.ErrorCode;
import com.maylin.enums.Status;
import com.maylin.exception.BaseException;
import com.maylin.mapper.IBookItemMapper;
import com.maylin.mapper.IBookMapper;
import com.maylin.mapper.ILoanMapper;
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
	private final ILoanMapper loanMapper;

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
	
	@Override
	public DtoBookItemDetailResponse getBookItemById(Long id) {
		BookItem bookItem=findBookItemById(id);
		return buildBookItemDetilResponse(bookItem);
		
	}
	
	@Override
	public void deleteBookItem(Long id) {
		BookItem bookitem=bookitemRepository.findById(id)
				.orElseThrow(()->new BaseException(ErrorCode.BOOKITEM_NOT_FOUND,HttpStatus.NOT_FOUND));
		if(bookitem.getStatus()==Status.BORROWED) {
			throw new BaseException(ErrorCode.BOOK_HAS_ACTIVE_LOAN,HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		bookitemRepository.delete(bookitem);
	}

	
	private Book findBookById(Long bookId) {
		return bookRepository.findByIdWithAuthor(bookId)
				.orElseThrow(()->new BaseException(ErrorCode.BOOK_NOT_FOUND,HttpStatus.NOT_FOUND));
		
	}
	
	private BookItem findBookItemById(Long id) {
		return bookitemRepository.findByIdWithLoans(id)
				.orElseThrow(()->new BaseException(ErrorCode.BOOKITEM_NOT_FOUND,HttpStatus.NOT_FOUND));
	}
	
	private DtoBookItemResponse buildBookItemResponse(BookItem bookitem) {
		
		DtoBookItemResponse response=bookitemMapper.toDtoBookItemResponse(bookitem);
		DtoBookShortResponse dtoBook=bookMapper.toBookShortResponse(bookitem.getBook());
		response.setBook(dtoBook);
		return response;
		
	}
	
	private DtoBookItemDetailResponse buildBookItemDetilResponse(BookItem bookitem) {
		DtoBookItemDetailResponse response=bookitemMapper.todtoBookItemDetailResponse(bookitem);
		DtoBookShortResponse dtoBook=bookMapper.toBookShortResponse(bookitem.getBook());
		List<DtoLoanForBookItem> dtoLoans=loanMapper.toDtoLoanForBookItemList(bookitem.getLoans());
		response.setBook(dtoBook);
		response.setLoans(dtoLoans);
		return response;
		
	}

	
	
	
	

}
