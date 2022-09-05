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
enum�� ������ �ڷ���(enumerated type)
�������� ���� ������ ������� ������ class�� ���� ��

(����� �빮��)

public enum Fruit{	
	APPLE,PEACH,BANANA;
}

public enum Company{
	GOOGLE,APPLE,SAMSUNG;
}

Fruit type = Fruit.BANANA;

switch(type){
	case APPLE:
		System.out.print("����Դϴ�");
		break;
	case PEACH:
		System.out.print("�������Դϴ�");
		break;
	case BANANA:
		System.out.print("�ٳ����Դϴ�");
		break;
}
		
*/