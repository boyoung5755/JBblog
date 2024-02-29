package com.innovationT.blog.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.innovationT.blog.member.entity.Member;
import com.innovationT.blog.member.repository.MemberRepository;
import com.innovationT.blog.security.auth.MemberPrincipalDetails;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/login")
public class LoginController {
	
	private final MemberRepository repository;
	
    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }
    
    
    @GetMapping("/") 
    public String welcomeLoginPage() {
    	return "login/login";
    }
    
    @GetMapping("/login/login")
    public String login(HttpServletRequest request,
                        @AuthenticationPrincipal MemberPrincipalDetails memberPrincipalDetails) {
        HttpSession session = request.getSession();
        String msg = (String) session.getAttribute("loginErrorMessage");
        session.setAttribute("loginErrorMessage", msg != null ? msg : "");

        if(isAuthenticated()) {
            if(memberPrincipalDetails == null)
                return "redirect:/login/logout";
            return "redirect:/board";
        }
        return "login/login";
    }

    
    @GetMapping("/login/mypage")
    public String text(@AuthenticationPrincipal MemberPrincipalDetails memberPrincipalDetails
                        ,Model model) {

        Member member = memberPrincipalDetails.getMember();

        model.addAttribute("member", member);
        return "text/text";
    }
    
    @GetMapping("/login/signUpForm")
    public String signUpForm() {
    	
        return "login/signup";
    }
    
    @PostMapping("/login/signup")
    @ResponseBody
    public String signUp(Member member) {
    	
    	String result = "";
    	
    	repository.save(member);
    	
    	return "/";
    }
}