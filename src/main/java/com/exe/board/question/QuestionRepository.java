package com.exe.board.question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**데이터 처리를 위해서는 실제 데이터베이스와 연동하는
 * JPA의 repository가 필요
 * 
 * 엔티티에의 생성된 데이터베이스 테이블에 접근하는 메소드를 
 * 사용하기 위한 인터페이스이다.(findAll,save)
 * CRUD : (Created Read,Update,Delete)를 정의하는 계층*/
public interface QuestionRepository extends JpaRepository<Question, Integer>{

	/** findBy + 엔티티명 */
	Question findBySubject(String subject);
	Question findBySubjectAndContent(String subject, String content);
	List<Question> findBySubjectLike(String subject);

	
	/** Pageable을 입력받아 Page<Question> 타입의 객체로 return */
	Page<Question> findAll(Pageable pageable);








}
