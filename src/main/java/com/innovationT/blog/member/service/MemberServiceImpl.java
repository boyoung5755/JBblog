package com.innovationT.blog.member.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.innovationT.blog.member.Member;
import com.innovationT.blog.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public Member createMember(Member member) {
		
		Member newMem = new Member();
		
		newMem.setMemId(member.getMemId());
		newMem.setEmail(member.getEmail());
		
		//비트크립 해시 함수
		newMem.setMemPw(passwordEncoder.encode(member.getMemPw()));
		//-> 빈으로 등록한 passwordEncoder 객체를 주입받아 사용
		
		this.memberRepository.save(newMem);
		return newMem;
	}
	
	

}
