package com.maylin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.maylin.dto.ApiResponse;
import com.maylin.dto.DtoLoanRequest;
import com.maylin.dto.DtoLoanResponse;
import com.maylin.dto.DtoLoanReturnResponse;
import com.maylin.dto.DtoLoanShortResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

public interface ILoanController {
	
	ResponseEntity<ApiResponse<DtoLoanResponse>> borrowBook(@Valid DtoLoanRequest request, HttpServletRequest httpRequest);
    ResponseEntity<ApiResponse<DtoLoanReturnResponse>> returnBook(@Min(1) Long loanId, HttpServletRequest httpRequest);
    ResponseEntity<ApiResponse<DtoLoanShortResponse>> getLoanById(@Min(1) Long loanId, HttpServletRequest httpRequest);
    ResponseEntity<ApiResponse<List<DtoLoanShortResponse>>> getOverdueLoans(HttpServletRequest httpRequest);
}
