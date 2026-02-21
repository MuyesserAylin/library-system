package com.maylin.controller;

import java.util.List;

import com.maylin.dto.DtoMemberRequest;
import com.maylin.dto.DtoMemberResponse;
import com.maylin.dto.DtoMemberShortResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

public interface IMemberController {
	
	public DtoMemberShortResponse saveMember(@Valid DtoMemberRequest request);
	
	public DtoMemberResponse getMemberById(@Min(value=1,message="Member ID cannot be blank")Long id);
	
	public List<DtoMemberShortResponse> getAllMembers();
	
	public void deleteMemberById(@Min(value=1,message="Member ID cannot be blank.")Long id);

}
