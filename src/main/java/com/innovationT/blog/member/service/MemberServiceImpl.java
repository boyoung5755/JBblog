package com.innovationT.blog.member.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.innovationT.blog.member.entity.Member;
import com.innovationT.blog.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {
	
	
	private final MemberRepository repo; 
	
	private final PasswordEncoder passwordEncoder;

	@Override
	public ResponseEntity<Member> signup(Member member) {
		
		String inputPassword = member.getMemPw();
		String encodePassword = passwordEncoder.encode(inputPassword);
		member.setMemPw(encodePassword);
		
		LocalDateTime inDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");
		
		String formattedDate = inDate.format(formatter);
		
		member.setMemInDate(formattedDate);
		
		Optional<Member> signMember = repo.findById(member.getId());
		
		if(!signMember.isPresent()) {
			Member newMember = Member.builder()
							.loginId(member.getLoginId())
							.memPw(member.getMemPw())
							.memNm(member.getMemNm())
							.memBirthDate(member.getMemBirthDate())
							.memNick(member.getMemNick())
							.memTelno(member.getMemTelno())
							.memEmail(member.getMemEmail())
							.memAdres1(member.getMemAdres1())
							.memAdres2(member.getMemAdres2())
							.memZip(member.getMemZip())
							.memRole("MEMBER")
							.memUsed("Y")
							.memDel("N")
							.memInDate(member.getMemInDate())
							.build();
			
			repo.save(member);
			
			return new ResponseEntity("success",HttpStatus.OK);
		}else {
			return new ResponseEntity("fail",HttpStatus.OK); 
		}
		
	}

}