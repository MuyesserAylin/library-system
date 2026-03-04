package com.maylin.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.maylin.dto.DtoBookItemRequest;
import com.maylin.dto.DtoBookItemResponse;
import com.maylin.dto.DtoBookItemShortResponse;
import com.maylin.model.BookItem;

@Mapper(componentModel = "spring")
public interface IBookItemMapper {
	
	DtoBookItemShortResponse todtoBookItemShortResponse(BookItem bookItem);
	
	List<DtoBookItemShortResponse> todtoBookShortList(List<BookItem> bookItem);
	
	@Mapping(target="book",ignore=true)
	BookItem toEntity(DtoBookItemRequest bookItemRequest);
	
	@Mapping(target="book",ignore=true)
	DtoBookItemResponse toDtoBookItemResponse(BookItem bookitem);
	
   
}
