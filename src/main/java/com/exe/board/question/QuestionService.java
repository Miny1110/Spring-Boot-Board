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
		
		/** �������� �ε����� 0���� �����Ѵ�.
		 * ������ �ε��� ��ȣ�� 0���� �����ϱ� ������ -1�� �� ���־�� �Ѵ�.
		 * �����ָ� 5�� ������ �� 6�������� ��*/
		pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber()-1,
				pageable.getPageSize(),Sort.by(sorts));
		
		/*
		getPageNumber : ��ȯ�� ������
		getPageSize : ��ȯ�� �׸� ��
		PageRequest : ���� �Ű������� ����� ���ο� �׸��� ����
		 */
		
		return questionRepository.findAll(pageable);
	}
	

	public Question getQuestion(Integer id) {
		
		Optional<Question> op = questionRepository.findById(id);
		
		if(op.isPresent())
			return op.get();
		else
			throw new DataNotFoundException("�����Ͱ� �����");
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




