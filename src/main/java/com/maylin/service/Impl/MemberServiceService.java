package com.maylin.service.Impl;

import org.springframework.stereotype.Service;

import com.maylin.dto.DtoMemberRequest;
import com.maylin.dto.DtoMemberResponse;
import com.maylin.mapper.IMemberMapper;
import com.maylin.model.Member;
import com.maylin.repository.IMemberRepository;
import com.maylin.service.IMemberService;
import com.maylin.util.StringUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceService  implements IMemberService{
	
	private final IMemberRepository memberRepository;
	private final IMemberMapper memberMapper;

	@Override
	public DtoMemberResponse saveMember(DtoMemberRequest request) {
		
		String cleanedEmail=StringUtil.formatEmail(request.getEmail());
		
		boolean exists=memberRepository.existsByEmailIgnoreCase(cleanedEmail);
		if(exists) {
			throw new RuntimeException("Bu email adresi zaten kullanımda.");
		}
		
		Member member=new Member();
		member.setFirstName(StringUtil.formatName(request.getFirstName()));
		member.setLastName(StringUtil.formatLastName(request.getLastName()));
		member.setEmail(cleanedEmail);
		member.setPassword(request.getPassword());//TODO:BCrypt eklenecek JWT ile
		
		Member savedMember=memberRepository.save(member);
		return memberMapper.toDtoMemberResponse(savedMember);
		
	}

}
