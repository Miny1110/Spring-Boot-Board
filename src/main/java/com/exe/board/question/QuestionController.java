package com.exe.board.question;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.exe.board.answer.AnswerForm;
import com.exe.board.user.SiteUser;
import com.exe.board.user.UserService;

import lombok.RequiredArgsConstructor;

/**@RequiredArgsConstructor : QuestionController의 오버로딩 생성자 만들기
 * 이걸 사용하기 위해서는 변수가 무조건 final이어야 한다.
 * HelloLombok 파일에서 했음*/

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

	/*
	 * QuestionService가 없었을 때에는 questionRepository와 questionRepository.findAll()를 썼다면
	 * QuestionService가 생긴 후에는 questionService와 questionService.getList() 사용
	 * 
	 * 전체 데이터를 불러오는 메소드를 service에 만들어 줌으로써, 
	 * 전체 데이터가 필요할 때에는 getList 메소드만 호출하면 된다.
	*/
	
	//private final QuestionRepository questionRepository;
	private final QuestionService questionService;
	private final UserService userService;
	
	/* @Get/PostMapping : get/post 방식으로 왔을 때 실행해라
	 * @RequestMapping : get/post 상관없이 실행해라. method는 디폴트 get방식이고 직접 적어줄 수 있음*/
	@RequestMapping("/list")
	public String list(Model model,@PageableDefault Pageable pageable) {
		
		//List<Question> lists = questionRepository.findAll();
		Page<Question> paging = questionService.getList(pageable);
		
		model.addAttribute("paging", paging);

		return "question_list";
	}
	
	
	//대표적인 템플릿들 : Thymeleaf, Mustache, Groovy, Freemarker, Velocity
	
	
	@RequestMapping("/detail/{id}")
	public String detil(Model model,@PathVariable("id") Integer id,AnswerForm answerForm) {
		
		Question question = questionService.getQuestion(id);
		
		model.addAttribute("question", question);
		
		return "question_detail";
	}
	
	/**isAuthenticated 는 이름을 변경하면 안됨*/
	@PreAuthorize("isAuthenticated")
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		
		return "question_form";
		
	}
	
	
	/*
	 * QuestionForm을 쓰고 BindingResult를 써야한다. QuestionForm에 가서 먼저 검사를 하고
	 * BindingResult이 실행되어야 하기 때문에
	 */
	@PreAuthorize("isAuthenticated")
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm,
			BindingResult bindingResult,Principal principal) {
		
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		
		SiteUser siteUser = userService.getUser(principal.getName());
		
		questionService.create(questionForm.getSubject(), questionForm.getContent(),siteUser);
		
		return "redirect:/question/list";
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String questionModify(QuestionForm questionForm, 
			@PathVariable("id") Integer id,Principal principal) {
		
		Question question = questionService.getQuestion(id);
		
		if(!question.getAuthor().getUserName().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정 권한이 없습니다");
		}
		
		questionForm.setSubject(question.getSubject());
		questionForm.setContent(question.getContent());
		
		return "question_form";
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String questionModify(@Valid QuestionForm questionForm,
			BindingResult bindingResult,@PathVariable Integer id,Principal principal) {
		
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		
		Question question = questionService.getQuestion(id);
		
		if(!question.getAuthor().getUserName().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정 권한이 없습니다");
		}
		
		questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
		
		return String.format("redirect:/question/detail/%s", id);
	}
	
	
	@PreAuthorize("isAuthenticated")
	@GetMapping("/delete/{id}")
	public String questionDelete(Principal principal,@PathVariable("id") Integer id) {
		
		Question question = questionService.getQuestion(id);
		
		if(!question.getAuthor().getUserName().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"삭제 권한이 없습니다");
		}
		
		questionService.delete(question);
		
		return "redirect:/";
		
	}
	
	
	@PreAuthorize("isAuthenticated")
	@GetMapping("/vote/{id}")
	public String questionVote(Principal principal,@PathVariable("id") Integer id) {
	
		Question question = questionService.getQuestion(id);
		
		SiteUser siteUser = userService.getUser(principal.getName());
		
		questionService.vote(question, siteUser);
		
		return String.format("redirect:/question/detail/%s", id);
		
		
		
		
		
	}
	
	
}
