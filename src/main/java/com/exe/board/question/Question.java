package com.exe.board.question;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.exe.board.answer.Answer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity //JPA�� ��ƼƼ�� �ν��Ѵ�
public class Question {
	
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 200)
	private String subject;
	
	//���ڼ� ���� ���� �� TEXT ���
	@Column(columnDefinition = "TEXT")
	private String content;
	
	
	private LocalDateTime createdDate;
	
	@OneToMany(mappedBy = "question",cascade = CascadeType.REMOVE,
			fetch = FetchType.EAGER) 
	//fetch : ����Ʈ���� lazy, ���� �����Ҷ����� db���� ������ ���´�.
	//			eager�� �����ϴٰ� �ѹ��� �� �����´�.
	private List<Answer> answerList;
	
}