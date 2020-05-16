package com.neighborsystem.util;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component("logger")
@Aspect //表示当前是一个切面类
public class Logger {
	@Pointcut("execution(* com.neighborsystem.service.impl.*.*(..))")
	private void pt1(){}


	/*
	* 前置通知
	*
	* */
	@Before("pt1()")
	public void beforePrintLog(){
		System.out.println("前置------------------");
	}
	/*
	 * 后置通知
	 *
	 * */
	@AfterThrowing("pt1()")
	public void afterReturningPrintLog(){
		System.out.println("后置");
	}
	/*
	 * 异常置通知
	 *
	 * */
	@AfterReturning("pt1()")
	public void afterThrowingPrintLog(){
		System.out.println("异常");
	}
	/*
	 * 最终通知
	 *
	 * */
	@After("pt1()")
	public void afterPrintLog(){
		System.out.println("最终置");
	}
//	/*
//	 * 环绕通知
//	 *
//	 * */
//	@Before(pt1())
//	public void beforePrintLog(){
//		System.out.println("环绕");
//	}



}
