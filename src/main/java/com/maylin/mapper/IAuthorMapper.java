package com.maylin.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.maylin.dto.DtoAuthorRequest;
import com.maylin.dto.DtoAuthorResponse;
import com.maylin.dto.DtoAuthorSummary;
import com.maylin.dto.DtoBookForAuthor;
import com.maylin.model.Author;
import com.maylin.model.Book;

@Mapper(componentModel = "spring")
public interface IAuthorMapper {
	
	
	@Mapping(target="authorName",expression="java(combineAuthorName(author))")
	@Mapping(target="books",ignore=true)
	DtoAuthorResponse toDtoAuthorResponse(Author author);
	
	Author toEntity(DtoAuthorRequest request);
	
	List<DtoAuthorResponse> toDtoList(List<Author> authors);
	
	
	
	@Mapping(target="authorName",expression="java(combineAuthorName(author))")
	DtoAuthorSummary toDtoAuthorSummary(Author author);
	
	
	
	default String  combineAuthorName(Author author) {
		
		if(author==null) {
			return "Unknown Author";	
		}
		String fName = (author.getFirstName() != null) ? author.getFirstName() : "";
	    String lName = (author.getLastName() != null) ? author.getLastName() : "";
		
		return (fName+" "+lName);
		
		
		
		
	}
	

}
