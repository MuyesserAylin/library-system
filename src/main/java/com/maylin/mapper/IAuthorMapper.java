package com.maylin.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.maylin.dto.DtoAuthorRequest;
import com.maylin.dto.DtoAuthorResponse;
import com.maylin.dto.DtoBookForAuthor;
import com.maylin.model.Author;
import com.maylin.model.Book;

@Mapper(componentModel="spring",uses= {IBookMapper.class})
public interface IAuthorMapper {
	
	
	Author toEntity(DtoAuthorRequest request);
	
	@Mapping(target="authorName",expression="java(combineAuthorName(author))")
	DtoAuthorResponse toDtoAuthorResponse(Author author);
	
	default String  combineAuthorName(Author author) {
		
		if(author==null) {
			return "";	
		}
		String fName = (author.getFirstName() != null) ? author.getFirstName() : "";
	    String lName = (author.getLastName() != null) ? author.getLastName() : "";
		
		return (fName+" "+lName);
		
		
		
		
	}
	

}
