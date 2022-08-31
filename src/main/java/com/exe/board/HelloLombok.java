package com.exe.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/** @RequiredArgsConstructor : 오버로딩 생성자를 만든다.
 * 								이걸 사용할 땐 변수를 무조건 상수(final)로 만들어야 한다.
 * 								그래서 setter를 사용할 수 없다. setter가 필요없다. */
@RequiredArgsConstructor
@Getter
//@Setter
public class HelloLombok {

	private final String name;
	private final int age;
	
	/*
	public static void main(String[] args) {
		
		HelloLombok hk = new HelloLombok("유인나",40);
		
		//hk.setName("배수지");
		//hk.setAge(27);
		
		System.out.println(hk.getName());
		System.out.println(hk.getAge());
	}
	*/
	
}
