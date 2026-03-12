package com.maylin.service;

import java.util.List;

import com.maylin.dto.DtoLoanRequest;
import com.maylin.dto.DtoLoanResponse;
import com.maylin.dto.DtoLoanReturnResponse;
import com.maylin.dto.DtoLoanShortResponse;
import com.maylin.model.Loan;

import jakarta.validation.Valid;

public interface ILoanService {
	
	public DtoLoanResponse borrowBook(DtoLoanRequest request);

	public DtoLoanReturnResponse returnBook(Long loanId);
	
	public DtoLoanShortResponse getLoanById(Long loanId);
	
	public List<DtoLoanShortResponse> getOverdueLoans();
	
	
}
