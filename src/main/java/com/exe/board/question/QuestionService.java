package com.exe.board.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.exe.board.DataNotFoundException;
import com.exe.board.user.SiteUser;

import lombok.RequiredArgsConstructor;

//Controller -> Service -> Repository

@RequiredArgsConstructor
@Service
public class QuestionService {

	private final QuestionRepository questionRepository;
	
	public Page<Question> getList(Pageable pageable){
	
		List<Sort.Order> sorts = new ArrayList<Sort.Order>();
		sorts.add(Sort.Order.desc("createdDate"));
		
		/** 스프링의 인덱스는 0부터 시작한다.
		 * 페이지 인덱스 번호가 0부터 시작하기 때문에 -1을 꼭 해주어야 한다.
		 * 안해주면 5를 눌렀을 때 6페이지가 뜸*/
		pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber()-1,
				pageable.getPageSize(),Sort.by(sorts));
		
		/*
		getPageNumber : 반환할 페이지
		getPageSize : 반환할 항목 수
		PageRequest : 정렬 매개변수가 적용된 새로운 항목을 생성
		 */
		
		return questionRepository.findAll(pageable);
	}
	

	public Question getQuestion(Integer id) {
		
		Optional<Question> op = questionRepository.findById(id);
		
		if(op.isPresent())
			return op.get();
		else
			throw new DataNotFoundException("데이터가 없어요");
	}


	public void create(String subject, String content, SiteUser author) {
		
		Question question = new Question();
		
		question.setSubject(subject);
		question.setContent(content);
		question.setCreatedDate(LocalDateTime.now());
		question.setAuthor(author);
		
		questionRepository.save(question);
	}
	


}




