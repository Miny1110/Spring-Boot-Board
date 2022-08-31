package com.exe.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController : @ResponseBody 없어도 된다. @Controller + @ResponseBody
 * 					데이터를 JSON으로 넘겨주기 위해서 사용한다.
 * @Controller : @ResponseBody 필요하다.
 * */
@RestController
public class HelloController {
	
	/**
	 * @RequestMapping 에서 value 생략 가능
	 * @ResponseBody : 리턴값을 파일주소로 이동하는 것이 아니라 텍스트 자체로 내보낼 때 사용 */
	@RequestMapping("/hello")
	//@ResponseBody
	public String hello() {
		return "hello World!";
	}

}
