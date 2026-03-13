package com.maylin.controller.Impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
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
import com.maylin.dto.ApiResponse;
import com.maylin.dto.DtoMemberRequest;
import com.maylin.dto.DtoMemberResponse;
import com.maylin.dto.DtoMemberShortResponse;
import com.maylin.dto.DtoMemberUpdate;
import com.maylin.service.IMemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path="/rest/api/member")
@RequiredArgsConstructor
@Validated
public class MemberControllerImpl extends RestBaseController implements IMemberController{
	
	private final IMemberService memberService;

	@Override
    @PostMapping("/save")
    public ResponseEntity<ApiResponse<DtoMemberShortResponse>> saveMember(@Valid @RequestBody DtoMemberRequest request, HttpServletRequest httpRequest) {
        return created(memberService.saveMember(request), httpRequest);
    }

    @Override
    @GetMapping("/list/{id}")
    public ResponseEntity<ApiResponse<DtoMemberResponse>> getMemberById(@PathVariable("id") @Min(1) Long id, HttpServletRequest httpRequest) {
        return ok(memberService.getMemberById(id), httpRequest);
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<DtoMemberShortResponse>>> getAllMembers(HttpServletRequest httpRequest) {
        return ok(memberService.getAllMembers(), httpRequest);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMemberById(@PathVariable("id") @Min(1) Long id, HttpServletRequest httpRequest) {
        memberService.deleteMemberById(id);
        return ok(null, httpRequest);
    }

    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<DtoMemberShortResponse>> updateMember(@PathVariable("id") @Min(1) Long id, @Valid @RequestBody DtoMemberUpdate updateMember, HttpServletRequest httpRequest) {
        return ok(memberService.updateMember(id, updateMember), httpRequest);
    }
}
