package com.innovationT.blog.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innovationT.blog.member.entity.Member;

@Mapper
public interface MemberMapper  {
	
	/**
	 * 스프링시큐리티 인증인가 세부정보
	 * @param memLoginId
	 * @return
	 */
	public Member selectUserInfo(String memLoginId);
	
	/**
	 * 회원가입
	 * @param member
	 * @return
	 */
	public int insertNewUser(Member member);
	
	/**
	 * 회원정보 수정
	 * @param member
	 * @return
	 */
	public int updateUser(Member member);
	
	/**
	 * 회원탈퇴
	 * @param memLoginId
	 * @return
	 */
	public int deleteUser(String memLoginId);
}