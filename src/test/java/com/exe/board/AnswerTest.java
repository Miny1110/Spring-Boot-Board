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
		
		//2���� �ش��ϴ� ������ ��۴ޱ�
		Optional<Question> op = questionRepository.findById(2);
		
		assertTrue(op.isPresent());
		Question q = op.get();
		
		Answer a = new Answer();
		a.setContent("JPA�� ORM�Դϴ�1");
		a.setQuestion(q);
		a.setCreatedDate(LocalDateTime.now());
		
		answerRepository.save(a);
		
	}
	*/
	
	@Test
	void replyFind() {
		
		//fk�� ����� question�� id ���� answer�� id
		Optional<Answer> op = answerRepository.findById(1);
		assertTrue(op.isPresent());
		
		Answer a = op.get();
		
		//question�� id�� 2���̳�
		assertEquals(2, a.getQuestion().getId());
		
	}
	
	/**@Transactional : �ϳ��� �۾��� ���������� ������ �����ض�. fetch�� ������� �ʾƵ���*/ 
	@Transactional
	@Test
	void replyConnQuestion() {
		
		//2�� ���� ������ �о�Ͷ�
		//�̶� �����͸� �о���� question���� ������ ����� �ȴ�.
		//�׷��� �� �ؿ��� q.getAnswerList���� ���۾��� �Ұ����ϴ�.
		//�̰� Test �Ҷ��� �׷���. ���� db������ ������
		//Test������ ����ϰ������ question.java���� fetch�� eager�� �������ָ� �ȴ�. 
		Optional<Question> op = questionRepository.findById(2);
		assertTrue(op.isPresent());
		Question q = op.get();
		
		//������ ���� �亯�� Question.java ������ answerList�� ����ִ�.
		
		List<Answer> answerList = q.getAnswerList();
		
		assertEquals(5, answerList.size());
		assertEquals("JPA�� ORM�Դϴ�", answerList.get(0).getContent());
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
