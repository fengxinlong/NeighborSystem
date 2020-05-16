package com.neighborsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/view")
public class ViewController {

	@RequestMapping("goodsList")
	public String pathControl(){
		return "admin/goodslist";
	}
	@RequestMapping("addGoods")
	public String toAddGoods(){
		return "admin/goodsadd";
	}
	@RequestMapping("login")
	public String toLogin(){

		return "login";
	}
	@RequestMapping("register")
	public String toRegister(){
		return "register";
	}
	@RequestMapping("usercenter")
	public String toUserCenter(){
		return "userview/usercenter";
	}
//	@RequestMapping("index")
//	public String toIndex(HttpServletRequest request, HttpServletResponse response){
//
//		//请求转发:
//
//
//		return "index";
//	}
	@RequestMapping("index")
	public ModelAndView toIndex(){
		ModelAndView mv = new ModelAndView();

		mv.addObject("msg","你今天还没有打卡哦");

		mv.setViewName("index");


		return mv;

	}
	@RequestMapping("cart")
	public String toCart(){
		return "userview/shopping_cart";
	}
	@RequestMapping("admin")
	public String toAdmin(){
		return "adminlogin";
	}
	@RequestMapping("orderlist")
	public String toOrderList(){
		return "admin/orderlist";
	}
	@RequestMapping("userlist")
	public String toUserList(){
		return "admin/userlist";
	}
	@RequestMapping("bannerlist")
	public String toBannerList(){
		return "admin/bannerlist";
	}
	@RequestMapping("addbanner")
	public String toAddBanner(){
		return "admin/banneradd";
	}
	@RequestMapping("evaluate")
	public String toEva(){
		return "userview/evaluate";
	}
	@RequestMapping("evalist")
	public String toEvalist(){
		return "admin/evalist";
	}
	@RequestMapping("welcome")
	public String toWelcome(){
		return "admin/welcome";
	}
	@RequestMapping("toAdminIndex")
	public String toAdminIndex(){
		return "admin/index";
	}
	@RequestMapping("admininfo")
	public String adminInfo(){
		return "admin/info";
	}
	@RequestMapping("updatepass")
	public String updatePass(){
		return "admin/updatepass";
	}
	@RequestMapping("typelist")
	public String toTypeList(){
		return "admin/typelist";
	}
	@RequestMapping("patient")
	public String toPatient(){
		return "userview/patientRegister";
	}
	@RequestMapping("patientslist")
	public String toPatientList(){return "admin/patientslist";}




}
