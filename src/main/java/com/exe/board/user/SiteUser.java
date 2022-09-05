package com.exe.board.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SiteUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true) //unique : �̸��� �ߺ����� �ʰ� �Ѵ�.
	private String userName;
	private String password;
	
	@Column(unique = true)
	private String email;
	
	/** unique�� ��� ������ �̹� ���̺� ����� �����Ϳ� ���ϰ����� �Է��� �õ��ϸ� ������ �߻��Ѵ�.*/
	
}
