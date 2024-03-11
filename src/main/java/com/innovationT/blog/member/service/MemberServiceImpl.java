package com.innovationT.blog.member.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.innovationT.blog.common.enumpkg.ServiceResult;
import com.innovationT.blog.member.entity.Member;
import com.innovationT.blog.member.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {
	
	
	private final MemberMapper mapper;

	@Override
	public Member retrieveUserInfo(String memLoginId) {
		Member member = mapper.selectUserInfo(memLoginId);
		
		return member;
	}
	
	@Override
	public ServiceResult createNewUser(Member member){
		
		int cnt = 0;
		ServiceResult res;
		Member members = null ;
		
		try {
			cnt = mapper.insertNewUser(member);
			members = mapper.selectUserInfo(member.getLoginId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(cnt > 0) {
			 res = ServiceResult.OK;
		}else if(members.getLoginId().equals(member.getLoginId())) {
			 res = ServiceResult.DUPLICATED;
		}else {
			res = ServiceResult.FAIL;
		}
		
		return res;
		
	}

	@Override
	public ServiceResult modifyUser(Member member) {
		int cnt = 0;
		ServiceResult res;
		try {
			cnt = mapper.updateUser(member);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(cnt > 0) {
			 res = ServiceResult.OK;
		}else {
			 res = ServiceResult.FAIL;
		}
		return res;
	}

	@Override
	public ServiceResult removeUser(String memLoginId) {
		int cnt = 0;
		ServiceResult res;
		try {
			cnt = mapper.deleteUser(memLoginId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(cnt > 0) {
			 res = ServiceResult.OK;
		}else {
			 res = ServiceResult.FAIL;
		}
		return res;
	}

	


}