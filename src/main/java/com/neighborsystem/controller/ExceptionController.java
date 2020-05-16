package com.neighborsystem.controller;

import com.neighborsystem.entity.SysException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exception")
public class ExceptionController {

	@RequestMapping("/index")
	public String toException(){
		return "exception";
	}

	@RequestMapping("/testException")
	public String testException() throws Exception{
		System.out.println("testException执行了...");

		try {
			//模拟异常
			int a= 10/0;
		} catch (Exception e) {
			e.printStackTrace();
			//抛出自定义异常信息
			throw new SysException("分母为0...");
		}

		return "success";
	}

	@RequestMapping("/testInterceptor")
	public String testInterceptor(){
		System.out.println("testInterceptor");

		return "success";
	}

}
