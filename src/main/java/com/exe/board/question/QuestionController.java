package com.exe.board.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

/**@RequiredArgsConstructor : QuestionController�� �����ε� ������ �����
 * �̰� ����ϱ� ���ؼ��� ������ ������ final�̾�� �Ѵ�.
 * HelloLombok ���Ͽ��� ����*/

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

	/*
	 * QuestionService�� ������ ������ questionRepository�� questionRepository.findAll()�� ��ٸ�
	 * QuestionService�� ���� �Ŀ��� questionService�� questionService.getList() ���
	 * 
	 * ��ü �����͸� �ҷ����� �޼ҵ带 service�� ����� �����ν�, 
	 * ��ü �����Ͱ� �ʿ��� ������ getList �޼ҵ常 ȣ���ϸ� �ȴ�.
	*/
	
	//private final QuestionRepository questionRepository;
	private final QuestionService questionService;
	
	/* @Get/PostMapping : get/post ������� ���� �� �����ض�
	 * @RequestMapping : get/post ������� �����ض�. method�� ����Ʈ get����̰� ���� ������ �� ����*/
	@RequestMapping("/list")
	public String list(Model model) {
		
		//List<Question> lists = questionRepository.findAll();
		List<Question> lists = questionService.getList();
		
		model.addAttribute("lists", lists);

		return "question_list";
	}
	
	
	//��ǥ���� ���ø��� : Thymeleaf, Mustache, Groovy, Freemarker, Velocity
	
	
	@RequestMapping("/detail/{id}")
	public String detil(Model model,@PathVariable("id") Integer id) {
		
		Question question = questionService.getQuestion(id);
		
		model.addAttribute("question", question);
		
		return "question_detail";
	}
	
	
	
	
	
	
	
	
	
}
