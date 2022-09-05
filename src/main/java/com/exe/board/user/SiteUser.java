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
	
	@Column(unique = true) //unique : 이름이 중복되지 않게 한다.
	private String userName;
	private String password;
	
	@Column(unique = true)
	private String email;
	
	/** unique를 줬기 때문에 이미 테이블에 저장된 데이터와 동일값으로 입력을 시도하면 에러가 발생한다.*/
	
}
