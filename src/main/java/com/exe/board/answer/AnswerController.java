package com.exe.board.answer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exe.board.question.Question;
import com.exe.board.question.QuestionService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

	private final QuestionService questionService;
	private final AnswerService answerService;
	
	/** @RequestMapping ���� ���� �ڿ� method Ÿ���� �����൵ �ȴ�.*/
	@PostMapping("/create/{id}")
	public String createAnswer(Model model,@PathVariable("id") Integer id,@RequestParam String content) {
		
		Question question = questionService.getQuestion(id);
		
		answerService.created(question, content);
		
		return String.format("redirect:/question/detail/%s", id);
		
		
	}
	
	
	
	
	
	
}