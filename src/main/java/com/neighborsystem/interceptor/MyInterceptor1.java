package com.neighborsystem.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor1 implements HandlerInterceptor {
	/*
	* 预处理,controller方法执行前
	* return true 放行，执行下一个拦截器，如果没有，执行controller
	* return false 不放行
	*
	* */

	/*
	* 做一些逻辑判断
	* */
	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
		System.out.println("拦截器执行了...前1111");
//		httpServletRequest.getRequestDispatcher("login.jsp").forward(httpServletRequest,httpServletResponse);
//		httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(httpServletRequest,httpServletResponse);
		return true;

	}

	/*
	*
	* 后处理的方法
	*	Controller方法执行后,success方法执行前
	* */
	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
		System.out.println("拦截器执行了...后1111");
//		httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(httpServletRequest,httpServletResponse);

	}
	/*
	* success.jsp方法执行后，会执行
	* 释放资源
	* */
	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
		System.out.println("拦截器执行了...终1111");
	}

}
