package com.exe.board.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.exe.board.DataNotFoundException;

import lombok.RequiredArgsConstructor;

//Controller -> Service -> Repository

@RequiredArgsConstructor
@Service
public class QuestionService {

	private final QuestionRepository questionRepository;
	
	public List<Question> getList(){
		return questionRepository.findAll();
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




