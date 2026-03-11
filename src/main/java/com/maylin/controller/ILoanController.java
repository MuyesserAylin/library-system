package com.maylin.controller;

import com.maylin.dto.DtoLoanRequest;
import com.maylin.dto.DtoLoanResponse;
import com.maylin.dto.DtoLoanReturnResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

public interface ILoanController {
	
	
	public DtoLoanResponse borrowBook(@Valid DtoLoanRequest request);
	
	public DtoLoanReturnResponse returnBook(@Min(value=1,message="Loan id must be at least 1.") @Valid Long loanId);
	
	
	
	
}
