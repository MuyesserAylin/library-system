package com.maylin.controller;

import com.maylin.dto.DtoMemberRequest;
import com.maylin.dto.DtoMemberResponse;

import jakarta.validation.Valid;

public interface IMemberController {
	
	public DtoMemberResponse saveMember(@Valid DtoMemberRequest request);

}
