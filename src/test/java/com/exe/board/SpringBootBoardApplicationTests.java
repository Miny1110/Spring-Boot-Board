package com.exe.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.xmlunit.util.Linqy;

import com.exe.board.question.Question;
import com.exe.board.question.QuestionRepository;

@SpringBootTest
class SpringBootBoardApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Test
	void contextLoads() {
	}
	
	
	@Test
	void save() {
		
		Question q1 = new Question();
		q1.setSubject("스프링 부트가 무엇인가요");
		q1.setContent("스프링에 대해 알고싶어요");
		q1.setCreatedDate(LocalDateTime.now());
		
		this.questionRepository.save(q1);
		
		Question q2 = new Question();
		q2.setSubject("JPA가 무엇인가요?");
		q2.setContent("JPA에 대해 알고싶어요");
		q2.setCreatedDate(LocalDateTime.now());
		
		this.questionRepository.save(q2);
		
	}
	
	
	/*
	@Test
	void findAll() {
		
		List<Question> lists = questionRepository.findAll();
		
		assertEquals(2, lists.size());
		
		Question q = lists.get(0);
		assertEquals("스프링 부트가 무엇인가요", q.getSubject());
		
	}
	
	
	@Test
	void findBySubject() {
		
		Question q = questionRepository.findBySubject("스프링 부트가 무엇인가요");
		
		assertEquals(1, q.getId());
		
	}
	
	
	@Test
	void findBySubjectAndContent() {
		
		Question q = questionRepository
				.findBySubjectAndContent("스프링 부트가 무엇인가요", "스프링에 대해 알고싶어요");
		
		assertEquals(1, q.getId());
		
	}
	
	
	@Test
	void findBySubjectLike() {
		
		List<Question> lists = questionRepository.findBySubjectLike("스프링%");
		
		Question q = lists.get(0);
		
		assertEquals("스프링 부트가 무엇인가요", q.getSubject());
		
	}
	
	
	@Test
	void update() {
		
		//Optional : 데이터가 없으면 널값을 반환 
		// 			데이터가 있으면 그 값이 op에 들어가고 없으면 널값
		Optional<Question> op = questionRepository.findById(1);
		assertTrue(op.isPresent());
		
		Question q = op.get();
		q.setSubject("스프링");
		q.setContent("스프링 부트");
		
		questionRepository.save(q);
		
		
	}
	
	
	@Test
	void delete() {
		
		//데이터의 개수가 2개인가?
		assertEquals(2, questionRepository.count());
		
		Optional<Question> op = questionRepository.findById(1);
		
		//데이터가 있으면 그 다음 코드로 넘어가고 없으면 에러 발생
		//일종의 if문 역할
		assertTrue(op.isPresent());
		
		Question q = op.get();
		questionRepository.delete(q);
		
		assertEquals(1, questionRepository.count());
		
	}
	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
