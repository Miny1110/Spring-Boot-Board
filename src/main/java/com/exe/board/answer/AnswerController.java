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
import org.springframework.web.bind.annotation.RequestParam;
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
	
	/** �亯�� �ٴ� �޼ҵ� */
	/** @RequestMapping ���� ���� �ڿ� method Ÿ���� �����൵ �ȴ�.*/
	@PreAuthorize("isAuthenticated")
	@PostMapping("/create/{id}")
	public String createAnswer(Model model,@PathVariable("id") Integer id,
			@Valid AnswerForm answerForm,BindingResult bindingResult,Principal principal) {
		
		Question question = questionService.getQuestion(id);
		SiteUser siteUser = userService.getUser(principal.getName());
		/* principal�� ������� ������ �޾Ƽ�
		 * ������� name���� userService�� getUser ȣ��
		 * �����Ͱ� ������ ������� �����͸� ��ȯ�޾Ƽ� siteUser�� �ִ´�.*/
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("question", question);
			
			return "question_detail";
		}
		
		answerService.created(question, answerForm.getContent(),siteUser);
		
		return String.format("redirect:/question/detail/%s", id);
		
		
	}
	
//----------------------------------------------------------------------------------------------	
	@PreAuthorize("isAuthenticated")
	@GetMapping("/modify/{id}")
	public String answerModify(AnswerForm answerForm,
			@PathVariable("id") Integer id,Principal principal) {
		
		Answer answer = answerService.getAnswer(id);
		
		if(!answer.getAuthor().getUserName().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"���� ������ �����ϴ�");
		}
		
		answerForm.setContent(answer.getContent());
		
		return "question_detail";
	}
	
	
	
	
}
