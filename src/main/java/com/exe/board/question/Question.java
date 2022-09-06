package com.exe.board.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.exe.board.answer.Answer;
import com.exe.board.user.SiteUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity //JPA가 엔티티로 인식한다
public class Question {
	
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 200)
	private String subject;
	
	//글자수 제한 없을 때 TEXT 사용
	@Column(columnDefinition = "TEXT")
	private String content;
	
	
	private LocalDateTime createdDate;
	
	@OneToMany(mappedBy = "question",cascade = CascadeType.REMOVE,
			fetch = FetchType.EAGER) 
	//fetch : 디폴트값은 lazy, 한줄 실행할때마다 db와의 연결을 끊는다.
	//			eager는 유지하다가 한번에 다 가져온다.
	private List<Answer> answerList;
	
	
	@ManyToOne
	private SiteUser author;
	
	private LocalDateTime modifyDate;
	
	
	@ManyToMany
	//set은 절대 중복값을 갖지않는다. 즉, 다중투표는 되지만 중복투표는 안된다
	Set<SiteUser> voter;
	
	
}
