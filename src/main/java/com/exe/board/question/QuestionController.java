package com.exe.board.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

/**@RequiredArgsConstructor : QuestionController의 오버로딩 생성자 만들기
 * 이걸 사용하기 위해서는 변수가 무조건 final이어야 한다.
 * HelloLombok 파일에서 했음*/
@RequiredArgsConstructor
@Controller
public class QuestionController {

	private final QuestionRepository questionRepository;
	
	/* @Get/PostMapping : get/post 방식으로 왔을 때 실행해라
	 * @RequestMapping : get/post 상관없이 실행해라. method는 디폴트 get방식이고 직접 적어줄 수 있음*/
	@RequestMapping("/question/list")
	public String list(Model model) {
		
		List<Question> lists = questionRepository.findAll();
	
		model.addAttribute("lists", lists);

		return "question_list";
	}
	
	
	//대표적인 템플릿들 : Thymeleaf, Mustache, Groovy, Freemarker, Velocity
	
	
	
	
	
	
	
}
