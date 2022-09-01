package com.exe.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.exe.board.answer.Answer;
import com.exe.board.answer.AnswerRepository;
import com.exe.board.question.Question;
import com.exe.board.question.QuestionRepository;

@SpringBootTest
public class AnswerTest {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	/*
	@Test
	void replySave() {
		
		//2번에 해당하는 질문에 답글달기
		Optional<Question> op = questionRepository.findById(2);
		
		assertTrue(op.isPresent());
		Question q = op.get();
		
		Answer a = new Answer();
		a.setContent("JPA는 ORM입니다1");
		a.setQuestion(q);
		a.setCreatedDate(LocalDateTime.now());
		
		answerRepository.save(a);
		
	}
	*/
	
	@Test
	void replyFind() {
		
		//fk로 연결된 question의 id 말고 answer의 id
		Optional<Answer> op = answerRepository.findById(1);
		assertTrue(op.isPresent());
		
		Answer a = op.get();
		
		//question의 id가 2번이냐
		assertEquals(2, a.getQuestion().getId());
		
	}
	
	/**@Transactional : 하나의 작업이 끝날때까지 연결을 유지해라. fetch를 사용하지 않아도됨*/ 
	@Transactional
	@Test
	void replyConnQuestion() {
		
		//2번 질문 있으면 읽어와라
		//이때 데이터를 읽어오면 question과의 연결은 끊기게 된다.
		//그래서 그 밑에서 q.getAnswerList로의 비교작업이 불가능하다.
		//이건 Test 할때만 그렇다. 실제 db에서는 가능함
		//Test에서도 사용하고싶으면 question.java에서 fetch를 eager로 설정해주면 된다. 
		Optional<Question> op = questionRepository.findById(2);
		assertTrue(op.isPresent());
		Question q = op.get();
		
		//질문에 대한 답변은 Question.java 파일의 answerList에 들어있다.
		
		List<Answer> answerList = q.getAnswerList();
		
		assertEquals(5, answerList.size());
		assertEquals("JPA는 ORM입니다", answerList.get(0).getContent());
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
