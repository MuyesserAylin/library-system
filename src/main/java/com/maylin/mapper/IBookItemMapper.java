package com.maylin.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.maylin.dto.DtoBookItemShortResponse;
import com.maylin.model.BookItem;

@Mapper(componentModel = "spring")
public interface IBookItemMapper {
	
	DtoBookItemShortResponse todtoBookItemShortResponse(BookItem bookItem);
	
	List<DtoBookItemShortResponse> todtoBookShortList(List<BookItem> bookItem);
   
}
