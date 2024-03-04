package com.innovationT.blog.member.service;

import com.innovationT.blog.member.Member;

public interface MemberService {

	/**
	 * 회원가입
	 * @param member
	 * @return
	 */
	public Member createMember(Member member);
	
}
