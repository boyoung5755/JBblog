package com.innovationT.blog.member.service;

import org.springframework.http.ResponseEntity;

import com.innovationT.blog.member.entity.Member;

public interface MemberService {
	
	//회원가입
	ResponseEntity signup(Member member);
}