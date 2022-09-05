package com.exe.board.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.exe.board.user.UserSecurityService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity //필수옵션
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	//스프링 시큐리티에 등록
	private final UserSecurityService userSecurityService;
	
	@Bean //메소드의 객체를 만든다.(메소드를 객체화)
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		//인증되지 않은 모든 요청을 허락
		http
		.authorizeRequests().antMatchers("/**").permitAll()
		.and()
		.csrf().ignoringAntMatchers("/h2-console/**")
		//	.disable().headers().frameOptions().disable()
		.and()
		.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(
				XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
		.and()
		.formLogin().loginPage("/user/login").defaultSuccessUrl("/") //로그인이 성공하면 루트로 가
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")) //주소와 일치하면 로그아웃
		.logoutSuccessUrl("/") //성공하면 루트로 가고
		.invalidateHttpSession(true); //세션 자체가 삭제
		;
		
		
		return http.build();
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
	}
	
	//스프링 시큐리티의 인증을 담당
	//AuthenticationManager의 Bean 생성시 스프링의 내부동작으로 인해
	//UserSecurityService와 PasswordEncoder(암호화)가 자동으로 설정된다.
	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
}

/*
CSRF(Cross Site Request Forgery)
웹사이트의 취약점 공격을 방지하기 위해 사용하는 기술
스프링 시큐어리티가 CSRF 토큰값을 세션을 통해 발행하고
웹페이지에서는 폼 전송시에 해당 토큰을 함께 전송해서
실제 웹페이지에서 작성된 데이터가 전달되는지를 검증하는 기술
*/