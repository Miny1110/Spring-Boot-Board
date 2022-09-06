package com.exe.board.answer;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.exe.board.question.Question;
import com.exe.board.user.SiteUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createdDate;
	
	//하나의 질문에 답변 여러개가 달릴 수 있다.
	//답변 : Many, 질문: One
	//Foreign Key 생성
	@ManyToOne //테이블을 조인한 것
	private Question question;
	
	
	@ManyToOne
	private SiteUser author;
	
	private LocalDateTime modifyDate;
	
	//@ManyToMany로 하면 새로운 테이블이 만들어진다.
	@ManyToMany
	Set<SiteUser> voter;
}
