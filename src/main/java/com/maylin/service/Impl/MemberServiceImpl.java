package com.maylin.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.maylin.dto.DtoMemberRequest;
import com.maylin.dto.DtoMemberResponse;
import com.maylin.dto.DtoMemberShortResponse;
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
			throw new RuntimeException("Bu email adresi zaten kullanımda.");
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
		
		Member member=findMember(id);
		//TODO:Kayıylı Member bulunamadı diye exception fırlat.
		
		return memberMapper.toDtoMemberResponse(member);
	}

	@Override
	public List<DtoMemberShortResponse> getAllMembers() {
		List<Member> memberList=memberRepository.findAll();
		return memberMapper.toDtoShortList(memberList);
	}

	@Override
	public void deleteMemberById(Long id) {
		Member member=findMember(id);
		
		boolean hasActiveLoan=member.getLoans().stream()
				.anyMatch(loan ->loan.getReturnDate()==null);
		
		if(hasActiveLoan) {
			throw new RuntimeException("Memberin aktif kitap kayıdı var silinemez.");
			//TODO:Özel execption fırlat .Aktif kitap kaydı var dıye
		}
		
		memberRepository.deleteById(id);
		
				
	}
	
	private Member findMember(Long id) {
		return memberRepository.findMemberWithLoans(id)
				.orElseThrow(()->new RuntimeException("Kayıtlı member bulunamadı."));
		//TODO:Kayıtlı exception bulunamadı dıye ozel exceptıon fırlat
	}

}
