package com.exe.board.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

/**@RequiredArgsConstructor : QuestionController�� �����ε� ������ �����
 * �̰� ����ϱ� ���ؼ��� ������ ������ final�̾�� �Ѵ�.
 * HelloLombok ���Ͽ��� ����*/
@RequiredArgsConstructor
@Controller
public class QuestionController {

	private final QuestionRepository questionRepository;
	
	/* @Get/PostMapping : get/post ������� ���� �� �����ض�
	 * @RequestMapping : get/post ������� �����ض�. method�� ����Ʈ get����̰� ���� ������ �� ����*/
	@RequestMapping("/question/list")
	public String list(Model model) {
		
		List<Question> lists = questionRepository.findAll();
	
		model.addAttribute("lists", lists);

		return "question_list";
	}
	
	
	//��ǥ���� ���ø��� : Thymeleaf, Mustache, Groovy, Freemarker, Velocity
	
	
	
	
	
	
	
}
