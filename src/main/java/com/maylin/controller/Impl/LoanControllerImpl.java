package com.maylin.controller.Impl;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maylin.controller.ILoanController;
import com.maylin.dto.DtoLoanRequest;
import com.maylin.dto.DtoLoanResponse;
import com.maylin.service.ILoanService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/rest/api/loan")
@RequiredArgsConstructor
@Validated
public class LoanControllerImpl implements ILoanController {

	private final ILoanService loanService;

	@Override
	@PostMapping("/borrow")
	public DtoLoanResponse borrowBook(@RequestBody DtoLoanRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
