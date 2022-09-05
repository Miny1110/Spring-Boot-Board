package com.exe.board.user;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserService userService;
	
	@GetMapping("/signup")
	public String signup(UserCreateForm userCreateForm) {
		
		return "signup_form";
	}
	
	
	@PostMapping("/signup")
	public String signup(@Valid UserCreateForm userCreateForm,BindingResult bindingResult) {
		
		//입력값 널값 검사. 널이면 에러메시지 출력하고 리턴하기 때문에 비밀번호 확인검사 진행X
		if(bindingResult.hasErrors()) {
			return "signup_form";
		}
		
		
		//비밀번호 확인
		if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			//컬럼명, 오류코드, 에러메세지
			//오류코드는 사용자 정의. 맘대로 적으면 됨
			bindingResult.rejectValue("password2", "passwordInCorrect","패스워드가 일치하지 않습니다.");
			
			return "signup_form";
		}
		
		
		try {
			
			userService.create(userCreateForm.getUserName(), 
					userCreateForm.getEmail(), userCreateForm.getPassword1());
			
		} catch (DataIntegrityViolationException e) {

			e.printStackTrace();
			
			bindingResult.reject("signupFaild","이미 등록된 사용자입니다.");
			return "signup_form";
			
		} catch (Exception e) {

			e.printStackTrace();
			
			bindingResult.reject("signupFaild", e.getMessage());
			return "signup_form";
		}

		return "redirect:/";
		
	}

	@GetMapping("/login")
	public String login() {
		
		return "login_form";
		
	}







}
