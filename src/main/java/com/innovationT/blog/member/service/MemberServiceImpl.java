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
	

	@Override
	public ResponseEntity<Member> signup(Member member) {
		

			
			repo.save(member);
			
			return new ResponseEntity("success",HttpStatus.OK);
		}else {
			return new ResponseEntity("fail",HttpStatus.OK); 
		}
		
	}

}