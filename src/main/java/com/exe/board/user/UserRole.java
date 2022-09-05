package com.exe.board.user;

import lombok.Getter;

@Getter
public enum UserRole {
	
	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");

	private String value;
	
	UserRole(String value){
		this.value = value;
	}
}

/*
enum은 열거형 자료형(enumerated type)
열거형은 서로 연관된 상수들의 집합을 class로 만든 것

(상수라서 대문자)

public enum Fruit{	
	APPLE,PEACH,BANANA;
}

public enum Company{
	GOOGLE,APPLE,SAMSUNG;
}

Fruit type = Fruit.BANANA;

switch(type){
	case APPLE:
		System.out.print("사과입니다");
		break;
	case PEACH:
		System.out.print("복숭아입니다");
		break;
	case BANANA:
		System.out.print("바나나입니다");
		break;
}
		
*/