package com.maylin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.maylin.dto.DtoBookShortResponse;
import com.maylin.model.Book;

@Mapper(componentModel = "spring")
public interface IBookMapper {
	
	@Mapping(target="authorName",expression= "java(combineAuthorName(book))")
	DtoBookShortResponse toBookShortResponse(Book book);
	
	default String combineAuthorName(Book book) {
		
		if(book==null  ||  book.getAuthor()==null) {
			return "";
		}
		
		String firstName=book.getAuthor().getFirstName();
		String lastName=book.getAuthor().getLastName();
		
		return (firstName+" "+lastName).trim();	
	}
	

}
