package com.maylin.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.maylin.dto.DtoMemberRequest;
import com.maylin.dto.DtoMemberResponse;
import com.maylin.dto.DtoMemberShortResponse;
import com.maylin.dto.DtoMemberUpdate;
import com.maylin.enums.ErrorCode;
import com.maylin.exception.BaseException;
import com.maylin.mapper.IMemberMapper;
import com.maylin.model.Member;
import com.maylin.repository.IMemberRepository;
import com.maylin.service.IMemberService;
import com.maylin.util.StringUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl  implements IMemberService{
	
	private final IMemberRepository memberRepository;
	private final IMemberMapper memberMapper;

	@Override
	public DtoMemberShortResponse saveMember(DtoMemberRequest request) {
		
		String cleanedEmail=StringUtil.formatEmail(request.getEmail());
		
		boolean exists=memberRepository.existsByEmailIgnoreCase(cleanedEmail);
		if(exists) {
			throw new BaseException(ErrorCode.EMAIL_ALREADY_EXISTS,HttpStatus.CONFLICT);
		}
		
		Member member=new Member();
		member.setFirstName(StringUtil.formatName(request.getFirstName()));
		member.setLastName(StringUtil.formatLastName(request.getLastName()));
		member.setEmail(cleanedEmail);
		member.setPassword(request.getPassword());//TODO:BCrypt eklenecek JWT ile
		
		Member savedMember=memberRepository.save(member);
		return memberMapper.toDtoMemberShortResponse(savedMember);
		
	}

	@Override
	public DtoMemberResponse getMemberById(Long id) {
		
		Member member=findMemberById(id);
		
		return memberMapper.toDtoMemberResponse(member);
	}

	@Override
	public List<DtoMemberShortResponse> getAllMembers() {
		List<Member> memberList=memberRepository.findAll();
		return memberMapper.toDtoShortList(memberList);
	}

	@Override
	public void deleteMemberById(Long id) {
		Member member=findMemberById(id);
		
		boolean hasActiveLoan=member.getLoans().stream()
				.anyMatch(loan ->loan.getReturnDate()==null);
		
		if(hasActiveLoan) {
			throw new BaseException(ErrorCode.MEMBER_HAS_ACTIVE_LOAN,HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		memberRepository.deleteById(id);
		
				
	}
	

	@Override
	public DtoMemberShortResponse updateMember(Long id, DtoMemberUpdate updateMember) {
		
		Member member=findMemberById(id);
		String fName=member.getFirstName();
		String lName=member.getLastName();
		String email=member.getEmail();
		
		if(isNotBlank(updateMember.getFirstName())) {
			fName=StringUtil.formatName(updateMember.getFirstName());
		}
		
		if(isNotBlank(updateMember.getLastName())) {
			lName=StringUtil.formatLastName(updateMember.getLastName());
		}
		
		if(isNotBlank(updateMember.getEmail())) {
			email=StringUtil.formatEmail(updateMember.getEmail());
			
			Optional<Member> optional=memberRepository.findByEmailIgnoreCase(email.toLowerCase());
			if(optional.isPresent() && optional.get().getId()!=member.getId()) {
				throw new BaseException(ErrorCode.EMAIL_ALREADY_EXISTS,HttpStatus.CONFLICT);
			}
		}
		
		
		member.setFirstName(fName);
		member.setLastName(lName);
		member.setEmail(email);
		
		
		return memberMapper.toDtoMemberShortResponse(memberRepository.save(member));
		
	}
	
	private Boolean isNotBlank(String value) {
		return value!=null && !value.isBlank();
	}
	
	private Member findMemberById(Long id) {
		return memberRepository.findMemberWithLoans(id)
				.orElseThrow(()->new BaseException(ErrorCode.MEMBER_NOT_FOUND,HttpStatus.NOT_FOUND));
	
	}

}
