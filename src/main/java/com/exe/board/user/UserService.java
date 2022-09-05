package com.exe.board.user;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.exe.board.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //오버로딩 생성자 만든다.
@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public SiteUser create(String userName,String email,String password) {
		
		SiteUser user = new SiteUser();
		
		user.setUserName(userName);
		user.setEmail(email);
		
		//비밀번호는 암호화해서 저장
		//BCrypt 해싱 함수를 사용해서 암호화
		//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(password));
		
		userRepository.save(user);
		
		return user;
		
	}
	
	
	public SiteUser getUser(String userName) {
		
		Optional<SiteUser> siteUser = userRepository.findByUserName(userName);
		
		if(siteUser.isPresent()) {
			return siteUser.get();
		}else {
			throw new DataNotFoundException("사용자가 없습니다");
		}
		
	}



}
