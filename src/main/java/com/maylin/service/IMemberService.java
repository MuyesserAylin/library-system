package com.maylin.service;

import java.util.List;

import com.maylin.dto.DtoMemberRequest;
import com.maylin.dto.DtoMemberResponse;
import com.maylin.dto.DtoMemberShortResponse;


public interface IMemberService {
	
	public DtoMemberShortResponse saveMember(DtoMemberRequest request);
	
	public DtoMemberResponse getMemberById(Long id);
	
	public List<DtoMemberShortResponse> getAllMembers();
	
	public void deleteMemberById(Long id);


}
