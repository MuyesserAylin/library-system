package com.maylin.service;

import com.maylin.dto.DtoLoanRequest;
import com.maylin.dto.DtoLoanResponse;

import jakarta.validation.Valid;

public interface ILoanService {
	
	public DtoLoanResponse borrowBook(DtoLoanRequest request);

}
