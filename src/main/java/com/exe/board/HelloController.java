package com.exe.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController : @ResponseBody ��� �ȴ�. @Controller + @ResponseBody
 * 					�����͸� JSON���� �Ѱ��ֱ� ���ؼ� ����Ѵ�.
 * @Controller : @ResponseBody �ʿ��ϴ�.
 * */
@RestController
public class HelloController {
	
	/**
	 * @RequestMapping ���� value ���� ����
	 * @ResponseBody : ���ϰ��� �����ּҷ� �̵��ϴ� ���� �ƴ϶� �ؽ�Ʈ ��ü�� ������ �� ��� */
	@RequestMapping("/hello")
	//@ResponseBody
	public String hello() {
		return "hello World!";
	}

}
