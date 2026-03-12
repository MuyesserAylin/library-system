package com.maylin.controller.Impl;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maylin.controller.IMemberController;
import com.maylin.dto.DtoMemberRequest;
import com.maylin.dto.DtoMemberResponse;
import com.maylin.dto.DtoMemberShortResponse;
import com.maylin.dto.DtoMemberUpdate;
import com.maylin.service.IMemberService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path="/rest/api/member")
@RequiredArgsConstructor
@Validated
public class MemberControllerImpl implements IMemberController{
	
	private final IMemberService memberService;

	@Override
	@PostMapping("/save")
	public DtoMemberShortResponse saveMember(@Valid @RequestBody DtoMemberRequest request) {
		return memberService.saveMember(request);
	}

	@Override
	@GetMapping("/list/{id}")
	public DtoMemberResponse getMemberById(@PathVariable("id")@Min(1)Long id) {
		return memberService.getMemberById(id);
	}

	@Override
	@GetMapping("/list")
	public List<DtoMemberShortResponse> getAllMembers() {
		return memberService.getAllMembers();
	}

	@Override
	@DeleteMapping("/delete/{id}")
	public void deleteMemberById(@PathVariable("id")@Min(1) Long id) {
		memberService.deleteMemberById(id);
		
	}

	@Override
	@PutMapping("/update/{id}")
	public DtoMemberShortResponse updateMember(@PathVariable("id") @Min(1)Long id,
			@Valid @RequestBody DtoMemberUpdate updateMember) {
		return memberService.updateMember(id, updateMember);
	}
	
	

}
