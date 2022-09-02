package com.exe.board.question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**������ ó���� ���ؼ��� ���� �����ͺ��̽��� �����ϴ�
 * JPA�� repository�� �ʿ�
 * 
 * ��ƼƼ���� ������ �����ͺ��̽� ���̺� �����ϴ� �޼ҵ带 
 * ����ϱ� ���� �������̽��̴�.(findAll,save)
 * CRUD : (Created Read,Update,Delete)�� �����ϴ� ����*/
public interface QuestionRepository extends JpaRepository<Question, Integer>{

	/** findBy + ��ƼƼ�� */
	Question findBySubject(String subject);
	Question findBySubjectAndContent(String subject, String content);
	List<Question> findBySubjectLike(String subject);

	
	/** Pageable�� �Է¹޾� Page<Question> Ÿ���� ��ü�� return */
	Page<Question> findAll(Pageable pageable);








}
