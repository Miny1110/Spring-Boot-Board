package com.exe.board;

import org.springframework.data.jpa.repository.JpaRepository;

/**������ ó���� ���ؼ��� ���� �����ͺ��̽��� �����ϴ�
 * JPA�� repository�� �ʿ�
 * 
 * ��ƼƼ���� ������ �����͹��̽� ���̺� �����ϴ� �޼ҵ带 
 * ����ϱ� ���� �������̽��̴�.(findAll,save)
 * CRUD : (Created Read,Update,Delete)�� �����ϴ� ����*/
public interface QuestionRepository extends JpaRepository<Question, Integer>{

	Question findBySubject(String subject);
	
}
