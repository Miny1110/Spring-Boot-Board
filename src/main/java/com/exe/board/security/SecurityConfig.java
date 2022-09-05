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
@EnableWebSecurity //�ʼ��ɼ�
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	//������ ��ť��Ƽ�� ���
	private final UserSecurityService userSecurityService;
	
	@Bean //�޼ҵ��� ��ü�� �����.(�޼ҵ带 ��üȭ)
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		//�������� ���� ��� ��û�� ���
		http
		.authorizeRequests().antMatchers("/**").permitAll()
		.and()
		.csrf().ignoringAntMatchers("/h2-console/**")
		//	.disable().headers().frameOptions().disable()
		.and()
		.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(
				XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
		.and()
		.formLogin().loginPage("/user/login").defaultSuccessUrl("/") //�α����� �����ϸ� ��Ʈ�� ��
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")) //�ּҿ� ��ġ�ϸ� �α׾ƿ�
		.logoutSuccessUrl("/") //�����ϸ� ��Ʈ�� ����
		.invalidateHttpSession(true); //���� ��ü�� ����
		;
		
		
		return http.build();
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
	}
	
	//������ ��ť��Ƽ�� ������ ���
	//AuthenticationManager�� Bean ������ �������� ���ε������� ����
	//UserSecurityService�� PasswordEncoder(��ȣȭ)�� �ڵ����� �����ȴ�.
	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
}

/*
CSRF(Cross Site Request Forgery)
������Ʈ�� ����� ������ �����ϱ� ���� ����ϴ� ���
������ ��ť�Ƽ�� CSRF ��ū���� ������ ���� �����ϰ�
�������������� �� ���۽ÿ� �ش� ��ū�� �Բ� �����ؼ�
���� ������������ �ۼ��� �����Ͱ� ���޵Ǵ����� �����ϴ� ���
*/