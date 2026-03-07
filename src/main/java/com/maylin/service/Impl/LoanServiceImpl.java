package com.maylin.service.Impl;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.maylin.dto.DtoLoanRequest;
import com.maylin.dto.DtoLoanResponse;
import com.maylin.enums.ErrorCode;
import com.maylin.enums.Status;
import com.maylin.exception.BaseException;
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
	private final IMemberRepository memberRepository;
	private final IBookItemRepository bookItemRepository;

	@Override
	public DtoLoanResponse borrowBook(DtoLoanRequest request) {
		Member member=findMemberById(request.getMemberId());
		BookItem bookitem=findBookItemByIdWithBook(request.getBookitemId());
		
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
		
		
		
		
		
	}
	
	private Member findMemberById(Long memberId) {
		return memberRepository.findById(memberId)
				.orElseThrow(()->new BaseException(ErrorCode.MEMBER_NOT_FOUND,HttpStatus.NOT_FOUND));
		
	}
	
	private BookItem findBookItemByIdWithBook(Long bookitemId) {
		return bookItemRepository.findByIdWithBook(bookitemId)
				.orElseThrow(()->new BaseException(ErrorCode.BOOKITEM_NOT_FOUND,HttpStatus.NOT_FOUND));
	}
	

}
