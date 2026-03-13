package com.maylin.controller.Impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maylin.controller.ILoanController;
import com.maylin.dto.ApiResponse;
import com.maylin.dto.DtoLoanRequest;
import com.maylin.dto.DtoLoanResponse;
import com.maylin.dto.DtoLoanReturnResponse;
import com.maylin.dto.DtoLoanShortResponse;
import com.maylin.service.ILoanService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/rest/api/loan")
@RequiredArgsConstructor
@Validated
public class LoanControllerImpl extends RestBaseController implements ILoanController {

	private final ILoanService loanService;

	@Override
    @PostMapping("/borrow")
    public ResponseEntity<ApiResponse<DtoLoanResponse>> borrowBook(@Valid @RequestBody DtoLoanRequest request, HttpServletRequest httpRequest) {
        return created(loanService.borrowBook(request), httpRequest);
    }

    @Override
    @PutMapping("/return/{loanId}")
    public ResponseEntity<ApiResponse<DtoLoanReturnResponse>> returnBook(@PathVariable("loanId") @Min(1) Long loanId, HttpServletRequest httpRequest) {
        return ok(loanService.returnBook(loanId), httpRequest);
    }

    @Override
    @GetMapping("/list/{loanId}")
    public ResponseEntity<ApiResponse<DtoLoanShortResponse>> getLoanById(@PathVariable("loanId") @Min(1) Long loanId, HttpServletRequest httpRequest) {
        return ok(loanService.getLoanById(loanId), httpRequest);
    }

    @Override
    @GetMapping("/overdue")
    public ResponseEntity<ApiResponse<List<DtoLoanShortResponse>>> getOverdueLoans(HttpServletRequest httpRequest) {
        return ok(loanService.getOverdueLoans(), httpRequest);
    }

	
	
	
	
}
