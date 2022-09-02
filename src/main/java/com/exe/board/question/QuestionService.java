package com.exe.board.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.exe.board.DataNotFoundException;

import lombok.RequiredArgsConstructor;

//Controller -> Service -> Repository

@RequiredArgsConstructor
@Service
public class QuestionService {

	private final QuestionRepository questionRepository;
	
	public Page<Question> getList(Pageable pageable){
	
		pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber()-1,
				pageable.getPageSize());
		
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
			throw new DataNotFoundException("Question Not Found");
	}


	public void create(String subject, String content) {
		
		Question question = new Question();
		
		question.setSubject(subject);
		question.setContent(content);
		question.setCreatedDate(LocalDateTime.now());
		
		questionRepository.save(question);
	}
	


}




