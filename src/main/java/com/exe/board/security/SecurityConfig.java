package com.exe.board.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@Configuration
@EnableWebSecurity //필수옵션
public class SecurityConfig {
	
	@Bean //메소드의 객체를 만든다.
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		//인증되지 않은 모든 요청을 허락
		http.authorizeRequests().antMatchers("/**").permitAll()
		.and()
		.csrf().ignoringAntMatchers("/h2-console/**")
			.disable().headers().frameOptions().disable()
		//.and()
		//.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(
		//		XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
		;
		
		
		return http.build();
		
	}
	
}

/*
CSRF(Cross Site Request Forgery)
웹사이트의 취약점 공격을 방지하기 위해 사용하는 기술
스프링 시큐어리티가 CSRF 토큰값을 세션을 통해 발행하고
웹페이지에서는 폼 전송시에 해당 토큰을 함께 전송해서
실제 웹페이지에서 작성된 데이터가 전달되는지를 검증하는 기술
*/