package com.maylin.service;

import com.maylin.dto.DtoLoanRequest;
import com.maylin.dto.DtoLoanResponse;
import com.maylin.dto.DtoLoanReturnResponse;
import com.maylin.model.Loan;

import jakarta.validation.Valid;

public interface ILoanService {
	
	public DtoLoanResponse borrowBook(DtoLoanRequest request);

	public DtoLoanReturnResponse returnBook(Long loanId);
	
	
}
