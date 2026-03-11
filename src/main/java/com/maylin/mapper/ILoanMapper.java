package com.maylin.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.maylin.dto.DtoLoanForBookItem;
import com.maylin.dto.DtoLoanResponse;
import com.maylin.dto.DtoLoanReturnResponse;
import com.maylin.dto.DtoLoanShortResponse;
import com.maylin.model.Loan;
import com.maylin.model.Member;

@Mapper(componentModel="spring")
public interface ILoanMapper {
	
	@Mapping(target="barcode",source="loan.bookItem.barcode")
	@Mapping(target="title",source="loan.bookItem.book.title")
	@Mapping(target="status",source="loan.bookItem.status")
	DtoLoanShortResponse toDtoLoanShortResponse(Loan loan);
	List<DtoLoanShortResponse>  toDtoList(List<Loan> loans);
	
	@Mapping(target="memberId",source="loan.member.id")
	@Mapping(target="memberName",expression="java(combineMemberName(loan.getMember()))")
	DtoLoanForBookItem toDtoLoanForBookItemResponse(Loan loan);
	List<DtoLoanForBookItem> toDtoLoanForBookItemList(List<Loan> loans);
	
	@Mapping(target="memberName",expression="java(combineMemberName(loan.getMember()))")
	@Mapping(target="bookTitle",source="loan.bookItem.book.title")
	@Mapping(target="barcode",source="loan.bookItem.barcode")
	DtoLoanResponse toDtoLoanResponse(Loan loan);
	
	@Mapping(target="memberName",expression="java(combineMemberName(loan.getMember()))")
	@Mapping(target="bookTitle",source="loan.bookItem.book.title")
	@Mapping(target="barcode",source="loan.bookItem.barcode")
	DtoLoanReturnResponse toDtoLoanReturnResponse(Loan loan);
	
	default String combineMemberName(Member member) {
		if(member==null) {
			return "Unknown Member";
		}
		
		String fName=(member.getFirstName()!=null) ? member.getFirstName() : "";
		String lName=(member.getLastName()!=null) ? member.getLastName() :"";
		
		return (fName+" "+lName);
	}
	

}
