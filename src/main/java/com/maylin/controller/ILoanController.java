package com.maylin.controller;

import java.util.List;

import com.maylin.dto.DtoLoanRequest;
import com.maylin.dto.DtoLoanResponse;
import com.maylin.dto.DtoLoanReturnResponse;
import com.maylin.dto.DtoLoanShortResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

public interface ILoanController {
	
	
	public DtoLoanResponse borrowBook(@Valid DtoLoanRequest request);
	
	public DtoLoanReturnResponse returnBook(@Min(value=1,message="Loan id must be at least 1.") @Valid Long loanId);
	
	public DtoLoanShortResponse getLoanById(@Min(value=1,message="Loan id must be at least 1.")@Valid Long loanId);
	
	public List<DtoLoanShortResponse> getOverdueLoans();
}
