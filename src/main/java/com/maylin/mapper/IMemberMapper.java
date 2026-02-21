package com.maylin.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.maylin.dto.DtoLoanShortResponse;
import com.maylin.dto.DtoMemberRequest;
import com.maylin.dto.DtoMemberResponse;
import com.maylin.dto.DtoMemberShortResponse;
import com.maylin.model.Loan;
import com.maylin.model.Member;

@Mapper(componentModel = "spring",uses={ILoanMapper.class})
public interface IMemberMapper {
	
	Member  toEntity(DtoMemberRequest request);
	
	@Mapping(target="memberName",expression="java(combineMemberName(member))")
	DtoMemberShortResponse toDtoMemberShortResponse(Member member);

	DtoMemberResponse toDtoMemberResponse(Member member);
	
	List<DtoMemberShortResponse> toDtoShortList(List<Member> members);
	
	
	default String combineMemberName(Member member) {
		if(member==null) return "Unknown Member";
		
		String fName = (member.getFirstName() != null) ? member.getFirstName() : "";
	    String lName = (member.getLastName() != null) ? member.getLastName() : "";
		
		return (fName+" "+lName).trim();
		
	}
	
	
	
	
	

}
