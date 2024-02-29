package com.innovationT.blog.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innovationT.blog.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	
	Member findByLoginId(String username);
	
	Member save(Member member);
	
}