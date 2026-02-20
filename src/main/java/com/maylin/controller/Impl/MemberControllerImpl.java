package com.maylin.controller.Impl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maylin.controller.IMemberController;
import com.maylin.dto.DtoMemberRequest;
import com.maylin.dto.DtoMemberResponse;
import com.maylin.service.IMemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path="/rest/api/member")
@RequiredArgsConstructor
public class MemberControllerImpl implements IMemberController{
	
	private final IMemberService memberService;

	@Override
	@PostMapping("/save")
	public DtoMemberResponse saveMember(@RequestBody DtoMemberRequest request) {
		return memberService.saveMember(request);
	}
	
	

}
