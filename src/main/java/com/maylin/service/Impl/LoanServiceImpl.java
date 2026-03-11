package com.maylin.service.Impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.maylin.dto.DtoLoanRequest;
import com.maylin.dto.DtoLoanResponse;
import com.maylin.dto.DtoLoanReturnResponse;
import com.maylin.enums.ErrorCode;
import com.maylin.enums.Status;
import com.maylin.exception.BaseException;
import com.maylin.mapper.ILoanMapper;
import com.maylin.model.BookItem;
import com.maylin.model.Loan;
import com.maylin.model.Member;
import com.maylin.repository.IBookItemRepository;
import com.maylin.repository.ILoanRepository;
import com.maylin.repository.IMemberRepository;
import com.maylin.service.ILoanService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements ILoanService {
	
	private final ILoanRepository loanRepository;
	private final ILoanMapper loanMapper;
	private final IMemberRepository memberRepository;
	private final IBookItemRepository bookItemRepository;
	
	@Value("${library.penalty.daily}")
	private double dailyPenalty;
	

	@Override
	public DtoLoanResponse borrowBook(DtoLoanRequest request) {
		Member member=findMemberById(request.getMemberId());
		BookItem bookitem=findBookItemByIdWithBook(request.getBookitemId());
		
		int overDueCount=loanRepository.hasOverDueLoansByMemberId(member.getId(),LocalDate.now());
		
		if(overDueCount>0) {
			throw new BaseException(ErrorCode.MEMBER_HAS_OVERDUE_LOANS,HttpStatus.UNPROCESSABLE_ENTITY,overDueCount);
			
		}
		
		if(bookitem.getStatus()==Status.BORROWED ||
				bookitem.getStatus()==Status.LOST) {
			throw new BaseException(ErrorCode.BOOKITEM_NOT_AVAILABLE,HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		int activeLoans=loanRepository.countActiveLoansByMemberId(member.getId());
		
		if(activeLoans>=5) {
			throw new BaseException(ErrorCode.MEMBER_LOAN_LIMIT_EXCEEDED,HttpStatus.UNPROCESSABLE_ENTITY,activeLoans);
		}
		
		bookitem.setStatus(Status.BORROWED);
		bookItemRepository.save(bookitem);
		
		Loan loan=new Loan();
		loan.setMember(member);
		loan.setBookItem(bookitem);
		loan.setLoanDate(LocalDate.now());
		loan.setDueDate(LocalDate.now().plusDays(20));
		
		Loan savedLoan=loanRepository.save(loan);
		
		return buildLoanResponse(savedLoan);	
		
	}
	

	@Override
	public DtoLoanReturnResponse returnBook(Long loanId) {
		Loan loan =loanRepository.findByIdWithDetails(loanId)
				.orElseThrow(()->new BaseException(ErrorCode.LOAN_NOT_FOUND, HttpStatus.NOT_FOUND));
		
		if(loan.getReturnDate()!=null) {
			throw new BaseException(ErrorCode.LOAN_ALREADY_RETURNED,HttpStatus.UNPROCESSABLE_ENTITY,loan.getReturnDate());
		}
		
		double penalty=0.0;
		if(LocalDate.now().isAfter(loan.getDueDate())) {
			long overdueDays=ChronoUnit.DAYS.between(loan.getDueDate(),LocalDate.now());
			penalty=overdueDays*dailyPenalty;
		}

		loan.getBookItem().setStatus(Status.AVAILABLE);
		bookItemRepository.save(loan.getBookItem());
		
		loan.setReturnDate(LocalDate.now());
		loanRepository.save(loan);
		return buildLoanReturnResponse(loan,penalty);
		
	}
	
	
	private Member findMemberById(Long memberId) {
		return memberRepository.findById(memberId)
				.orElseThrow(()->new BaseException(ErrorCode.MEMBER_NOT_FOUND,HttpStatus.NOT_FOUND));
		
	}
	
	private BookItem findBookItemByIdWithBook(Long bookitemId) {
		return bookItemRepository.findByIdWithBook(bookitemId)
				.orElseThrow(()->new BaseException(ErrorCode.BOOKITEM_NOT_FOUND,HttpStatus.NOT_FOUND));
	}
	
	private DtoLoanResponse buildLoanResponse(Loan loan) {
		return loanMapper.toDtoLoanResponse(loan);
	}
	
	private DtoLoanReturnResponse buildLoanReturnResponse(Loan loan,Double penalty) {
		DtoLoanReturnResponse response= loanMapper.toDtoLoanReturnResponse(loan);
		response.setPenalty(penalty);
		return response;
		
		
		
	}

}
