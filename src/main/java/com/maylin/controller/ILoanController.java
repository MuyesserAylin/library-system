package com.maylin.controller;

import com.maylin.dto.DtoLoanRequest;
import com.maylin.dto.DtoLoanResponse;

import jakarta.validation.Valid;

public interface ILoanController {
	
	
	public DtoLoanResponse borrowBook(@Valid DtoLoanRequest request);
	
	
	
	
}
