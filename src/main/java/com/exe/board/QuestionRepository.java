package com.exe.board;

import org.springframework.data.jpa.repository.JpaRepository;

/**데이터 처리를 위해서는 실제 데이터베이스와 연동하는
 * JPA의 repository가 필요
 * 
 * 엔티티에의 생성된 데이터배이스 테이블에 접근하는 메소드를 
 * 사용하기 위한 인터페이스이다.(findAll,save)
 * CRUD : (Created Read,Update,Delete)를 정의하는 계층*/
public interface QuestionRepository extends JpaRepository<Question, Integer>{

	Question findBySubject(String subject);
	
}
