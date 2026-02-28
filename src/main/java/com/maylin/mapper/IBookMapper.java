package com.maylin.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.maylin.dto.DtoBookForAuthor;
import com.maylin.dto.DtoBookListResponse;
import com.maylin.dto.DtoBookRequest;
import com.maylin.dto.DtoBookResponse;
import com.maylin.dto.DtoBookShortResponse;
import com.maylin.model.Book;

@Mapper(componentModel = "spring")
public interface IBookMapper {
	
	@Mapping(target="authorName",expression= "java(combineAuthorName(book))")
	DtoBookShortResponse toBookShortResponse(Book book);
	List<DtoBookShortResponse> toBookShortList(List<Book> books);
	
	@Mapping(target="author",ignore=true)
	@Mapping(target="categories",ignore=true)
	DtoBookListResponse toDtoBookResponseList(Book book);
	List<DtoBookListResponse> toDtoBookResponseListList(List<Book> book);
	
	DtoBookForAuthor toDtoBookForAuthor(Book book);
	
    Book toEntity(DtoBookRequest request);
    
    
    DtoBookResponse toDtoBookResponse(Book book);
	
	List<DtoBookForAuthor> toDtoBookForAuthorList(List<Book> books);
	
	default String combineAuthorName(Book book) {
		
		if(book==null  ||  book.getAuthor()==null) {
			return "Unknown Author";
		}
		
		String firstName=book.getAuthor().getFirstName();
		String lastName=book.getAuthor().getLastName();
		
		return (firstName+" "+lastName).trim();	
	}
	

}
