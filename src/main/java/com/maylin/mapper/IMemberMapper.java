package com.maylin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.maylin.dto.DtoMemberRequest;
import com.maylin.dto.DtoMemberResponse;
import com.maylin.model.Member;

@Mapper(componentModel = "spring")
public interface IMemberMapper {
	
	Member  toEntity(DtoMemberRequest request);
	
	@Mapping(target="memberName",expression="java(combineMemberName(member))")
	DtoMemberResponse toDtoMemberResponse(Member member);
	
	default String combineMemberName(Member member) {
		if(member==null) return "Unknown Member";
		
		String fName = (member.getFirstName() != null) ? member.getFirstName() : "";
	    String lName = (member.getLastName() != null) ? member.getLastName() : "";
		
		return (fName+" "+lName).trim();
		
	}
	
	
	

}
