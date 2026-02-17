package com.maylin.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.maylin.dto.DtoCategoryRequest;
import com.maylin.dto.DtoCategoryResponse;
import com.maylin.dto.DtoCategoryShortResponse;
import com.maylin.model.Category;

@Mapper(componentModel="spring",uses = {IBookMapper.class})
public interface ICategoryMapper {
	
	//Donus tipi || func adı   || kaynak
	Category toEntity(DtoCategoryRequest request);
	
	DtoCategoryResponse toCategoryResponse(Category category);
	
	DtoCategoryShortResponse toCategoryShortResponse(Category category);
	
	List<DtoCategoryShortResponse> toCategoryShortResponseList(List<Category> catgeories);

}
