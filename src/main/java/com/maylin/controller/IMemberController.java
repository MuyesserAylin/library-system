package com.maylin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.maylin.dto.ApiResponse;
import com.maylin.dto.DtoMemberRequest;
import com.maylin.dto.DtoMemberResponse;
import com.maylin.dto.DtoMemberShortResponse;
import com.maylin.dto.DtoMemberUpdate;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

public interface IMemberController {
	
	ResponseEntity<ApiResponse<DtoMemberShortResponse>> saveMember(@Valid DtoMemberRequest request, HttpServletRequest httpRequest);
    ResponseEntity<ApiResponse<DtoMemberResponse>> getMemberById(@Min(1) Long id, HttpServletRequest httpRequest);
    ResponseEntity<ApiResponse<List<DtoMemberShortResponse>>> getAllMembers(HttpServletRequest httpRequest);
    ResponseEntity<ApiResponse<Void>> deleteMemberById(@Min(1) Long id, HttpServletRequest httpRequest);
    ResponseEntity<ApiResponse<DtoMemberShortResponse>> updateMember(@Min(1) Long id, @Valid DtoMemberUpdate updateMember, HttpServletRequest httpRequest);

}
