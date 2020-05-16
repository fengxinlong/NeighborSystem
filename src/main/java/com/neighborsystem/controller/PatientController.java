package com.neighborsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.neighborsystem.entity.Patient;
import com.neighborsystem.entity.TestUsers;
import com.neighborsystem.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/patient")
public class PatientController {
	@Autowired
	private IPatientService patientService;


//	@RequestMapping("/addPatient")
//	@ResponseBody
//	public String addPatient(ModelAndView model ,@RequestParam String name, @RequestParam String sex, @RequestParam String id
//	, @RequestParam String telephone, @RequestParam String option, @RequestParam String radio1, @RequestParam String radio2, @RequestParam String desc){
//
//		Patient patient = new Patient();
//		patient.setPatientName(name);
//		patient.setPatientSex(sex);
//		patient.setPatientPhone(telephone);
//		patient.setPatientState(Integer.parseInt(option));
//		patient.setOneStatus(radio1);
//		patient.setTwoStatus(radio2);
//		patient.setPatientContent(desc);
//
//		Integer rs = patientService.addPatient(patient);
//		System.out.println(rs);
//
//		return "index";
//
////		Integer rs = patientService.addPatient();
////		if(rs>0){
////			return "index";
////
////		}else{
////			return "index";
////		}
//	}

	@RequestMapping("addPatient")
	public String addPatient(Patient patient){
		Integer rs = patientService.addPatient(patient);
		if(rs>0){
			return "redirect:showSuccessinfos";
		}else{
			return "redirect:showFailinfos";
		}

	}

	@RequestMapping("findPatientBySplitPage")
	@ResponseBody
	public JSONObject findPatientBySplitPage(Integer page,Integer limit,String keyword){
		PageInfo<Patient> info = patientService.findBySplitPage(page, limit,keyword);
		JSONObject obj=new JSONObject();
		obj.put("msg", "");
		obj.put("code", 0);
		obj.put("count", info.getTotal());
		obj.put("data", info.getList());
		return obj;
	}

	@RequestMapping("showSuccessinfos")
	public String showSuccessInfos(Model model){
		model.addAttribute("msg","您今天已经成功打卡");
		System.out.println("显示成功");
		return "index";
	}

	@RequestMapping("showFailinfos")
	public String showErrorInfos(Model model){
		model.addAttribute("msg","亲,您今天还没有打卡");
		System.out.println("显示成功");
		return "index";
	}

	/*
	* 请求转发一次请求，不用编写项目的名称
	*
	* */
	@RequestMapping("/testVoid")
		public void testVoid(HttpServletRequest request, HttpServletResponse response) throws  Exception {
		System.out.println("testVoid方法执行了");
		//编写请求转发 手动调不会帮你执行视图解析器
//		request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request,response );

		/*
		* 重定向是重新发请求,是不能访问WEB-INF配置里面的东西
		* */
//		response.sendRedirect(request.getContextPath()+"/error.jsp");
//		System.out.println("重定向成功");

		//设置中文乱码
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//直接会进行响应
		response.getWriter().print("你好");
		response.getWriter().print("世界");

		return;

	}


	/*
	* ModelAndView
	* */
	@RequestMapping("testModelAndView")
	public ModelAndView testModelAndView(){
		//创建ModelAndView对象
		ModelAndView mv = new ModelAndView();
		TestUsers user = new TestUsers();
		user.setUname("帅帅");
		user.setUage("29");
		user.setUbirthday("2020-1-3");

		//把user对象存储到mv对象中,也会把user对象存入到request对象中
		mv.addObject("user",user);

		//跳转到哪个页面
		mv.setViewName("success");

		return mv;
	}
	/*
	* 使用关键字方式进行转发或者重定向
	* */
	@RequestMapping("testForwardOrRedirect")
	public String testForwardOrRedirect(){
		System.out.println("testForwardOrRedirect方法执行了");

//		//请求转发
//		return "forward:/WEB-INF/jsp/success.jsp";
		//重定向
		return "redirect:error.jsp";
	}




}
