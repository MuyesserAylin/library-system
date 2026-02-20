package com.maylin.service;

import com.maylin.dto.DtoMemberRequest;
import com.maylin.dto.DtoMemberResponse;

import jakarta.validation.Valid;

public interface IMemberService {
	
	public DtoMemberResponse saveMember(DtoMemberRequest request);


}
