package com.maylin.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.maylin.dto.DtoLoanShortResponse;
import com.maylin.model.Loan;

@Mapper(componentModel="spring")
public interface ILoanMapper {
	
	@Mapping(target="barcode",source="loan.bookItem.barcode")
	@Mapping(target="title",source="loan.bookItem.book.title")
	DtoLoanShortResponse toDtoLoanShortResponse(Loan loan);
	
	
	List<DtoLoanShortResponse>  toDtoList(List<Loan> loans);

}
