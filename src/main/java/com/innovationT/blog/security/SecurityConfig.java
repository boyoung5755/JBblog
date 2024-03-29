package com.innovationT.blog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity  // 모든 요청 url이 시큐리티의 제어를 받도록 만드는 어노테이션
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain filtercaChain(HttpSecurity http) throws Exception{
	  
	  http
	    .csrf(csrf ->csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
	    .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
	    .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
	    .headers((headers) -> headers
	      .addHeaderWriter(new XFrameOptionsHeaderWriter(
	        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)));
	        
	  return http.build();
	}

	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
