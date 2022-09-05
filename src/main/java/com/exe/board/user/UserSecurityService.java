package com.exe.board.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService{

	private final UserRepository userRepository;
	
	//사용자명으로 비밀번호를 조회해서 리턴하는 메소드
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		//사용자명으로 SiteUser 객체를 조회
		Optional<SiteUser> searchUser = userRepository.findByUserName(userName);
		
		if(!searchUser.isPresent()) { //isEmpty()
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다");
		}
		
		SiteUser siteUser = searchUser.get();
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		if("admin".equals(userName)) {
			authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
		}else {
			authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
		}
		
		//사용자명, 비밀번호, 권한을 입력해서 스프링 시큐리티의 user객체를 리턴
		//내가 만든 유저가 아니라 스프링 자체에 있는 유저
		return new User(siteUser.getUserName(), siteUser.getPassword(), authorities);
	}

}
