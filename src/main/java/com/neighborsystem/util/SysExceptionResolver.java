package com.neighborsystem.util;

import com.neighborsystem.entity.SysException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SysExceptionResolver implements HandlerExceptionResolver{


	/*
	* 当Controller真的把异常抛出后 需要处理异常逻辑
	*
	* o 当前处理器对象
	* */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, Exception e) {
		//获取到异常对象
		SysException exception =null;
		if(e instanceof SysException){
			exception=(SysException) e;
		}else {
			exception=new SysException("系统正在维护...");
		}
		System.err.println("访问"+request.getRequestURI()+"发生错误,错误信息:"+e.getMessage());

		//创建ModelAndView对象
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMsg", exception.getMessage());

		mv.setViewName("success");
		return mv;
	}
}
