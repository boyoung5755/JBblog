package com.innovationT.blog.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/mypage")
public class MypageController {
	
	
	@GetMapping()
	public String mypage() {
		return "page/mypage/mypage";
	}
	
	@ResponseBody
	@GetMapping("/test")
	public String test() {return "안녕";}
	

}
