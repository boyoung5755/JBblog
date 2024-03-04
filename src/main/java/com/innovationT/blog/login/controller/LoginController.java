package com.innovationT.blog.login.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.innovationT.blog.member.entity.Member;
import com.innovationT.blog.member.repository.MemberRepository;
import com.innovationT.blog.security.auth.MemberPrincipalDetails;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/login")
public class LoginController {
	
	private final MemberRepository repository;
	
	private final PasswordEncoder passwordEncoder;
	
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
    public String signUp(Member member) {
    	
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
    	
    	repository.save(member);
    	
		}
    	return "/";
    }
}