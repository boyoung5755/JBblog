package com.innovationT.blog.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.innovationT.blog.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;
	
	@GetMapping("/signup")
	public String signup() {
		return "page/member/signupForm";
	}
	
	@PostMapping("/signup")
	@ResponseBody
	public Map<String, String> singup(
		@RequestBody Member member
		) {
		Map<String, String> map = new HashMap<>();
		try {
			memberService.createMember(member);
			map.put("success", "Y");
		} catch (Exception e) {
			map.put("success", "N");
		}
		return map;
	}
	
	
}
