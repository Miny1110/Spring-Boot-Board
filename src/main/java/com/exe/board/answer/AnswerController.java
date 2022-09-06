package com.exe.board.answer;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.exe.board.question.Question;
import com.exe.board.question.QuestionService;
import com.exe.board.user.SiteUser;
import com.exe.board.user.UserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

	private final QuestionService questionService;
	private final AnswerService answerService;
	private final UserService userService;
	
	/** 답변을 다는 메소드 */
	/** @RequestMapping 으로 쓰고 뒤에 method 타입을 적어줘도 된다.*/
	@PreAuthorize("isAuthenticated")
	@PostMapping("/create/{id}")
	public String createAnswer(Model model,@PathVariable("id") Integer id,
			@Valid AnswerForm answerForm,BindingResult bindingResult,Principal principal) {
		
		Question question = questionService.getQuestion(id);
		SiteUser siteUser = userService.getUser(principal.getName());
		/* principal로 사용자의 정보를 받아서
		 * 사용자의 name으로 userService의 getUser 호출
		 * 데이터가 있으면 사용자의 데이터를 반환받아서 siteUser에 넣는다.*/
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("question", question);
			
			return "question_detail";
		}
		
		answerService.created(question, answerForm.getContent(),siteUser);
		
		return String.format("redirect:/question/detail/%s", id);
		
	}
	

	@PreAuthorize("isAuthenticated")
	@GetMapping("/modify/{id}")
	public String answerAnswer(AnswerForm answerForm,@PathVariable("id") Integer id,
			Principal principal) {
		
		Answer answer = answerService.getAnswer(id);
		
		if(!answer.getAuthor().getUserName().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정 권한이 없습니다");
		}
		
		answerForm.setContent(answer.getContent());
		
		return "answer_form";
		
	}
	
	
	@PreAuthorize("isAuthenticated")
	@PostMapping("/modify/{id}")
	public String answerModify(@Valid AnswerForm answerForm,BindingResult bindingResult,
			@PathVariable("id") Integer id,Principal principal) {
		
		if(bindingResult.hasErrors()) {
			return "answer_form";
		}
		
		Answer answer = answerService.getAnswer(id);
		
		if(!answer.getAuthor().getUserName().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정 권한이 없습니다");
		}
		
		answerService.modify(answer, answerForm.getContent());
		
		return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());

	}
	
	
	@PreAuthorize("isAuthenticated")
	@GetMapping("/delete/{id}")
	public String answerDelete(@PathVariable("id") Integer id,Principal principal) {
		
		Answer answer = answerService.getAnswer(id);
		
		if(!answer.getAuthor().getUserName().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"삭제 권한이 없습니다");
		}
		
		answerService.delete(answer);
		
		return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
		
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
