package com.neighborsystem.controller;

import com.neighborsystem.entity.TestUsers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

//控制器类
/*
* 需要一个类的方法执行，需要将类变成对象  开启注解扫描
*
* */
@Controller
@RequestMapping("/anno")
public class StarterController {
	@RequestMapping(path="/hello")
	public String sayHello(){
		System.out.println("helloSpringMVC");
		return "success";
	}

	@RequestMapping("/test")
	public String toIndex(){
		return "Param";
	}
	@RequestMapping(path="/requestMapping",params = {"username"})
	public String testRequestMapping(){
		System.out.println("测试RequestMapping注解");
		return "quickStart";
	}
	@RequestMapping("/p")
	public String testParam(String username){
		System.out.println("输出了");
		System.out.println("你的用户名"+username);

		return "quickStart";

	}
	@RequestMapping("/testServlet")
	public String testServlet(HttpServletRequest request, HttpServletResponse response){

		System.out.println(request);
		HttpSession session = request.getSession();
		System.out.println(session);
		System.out.println("执行了");
		ServletContext servletContext = session.getServletContext();
		System.out.println(servletContext);
		System.out.println(response);

		return "quickStart";
	}

	@RequestMapping("/testRequestParam")
	public String testRequestParam(@RequestParam(name = "name") String username){
		System.out.println("执行了....."+username);
		return "anno";
	}
	/*
	* 获取到请求体的内容
	* */
	@RequestMapping("/testRequestBody")
	public String testRequestBody(@RequestBody String body){
		System.out.println(body);
		return "anno";
	}

	/*
	 * PathVariable
	 * */
	@RequestMapping("/testPathVariable/{sid}")
	public String testPathVariable(@PathVariable("sid") String id){
		System.out.println(id);
		return "anno";
	}

	@RequestMapping("/testModelAttribute")
	public String testModelAttribute(TestUsers user){
		System.out.println("testModelAttribute执行了");
		System.out.println(user);
		return "anno";
	}
	/*
	* 带返回值
	* 该方法会先执行
	* *//*
	@ModelAttribute
	public TestUsers showUser(String uname){
		System.out.println("showUser执行了");
		//通过用户名查询数据库（模拟）
		TestUsers user=new TestUsers();
		user.setUname(uname);
		user.setUage("20");
		user.setUbirthday("2022-2-13");
		return  user;

}*/

// * 不带返回值
//// * 该方法会先执
// *

	@ModelAttribute
	public void showUser(String uname, @ModelAttribute("abc") Map<String,TestUsers> map){
		System.out.println("showUser执行了");
		//通过用户名查询数据库（模拟）
		TestUsers user=new TestUsers();
		user.setUname(uname);
		user.setUage("20");
		user.setUbirthday("2022-2-13");
		map.put("abc",user);

	}


//	/*
//	 * 不带返回值
//	 * 该方法会先执
//	 * */
//	@RequestMapping("/testSessionAttributes")
//	public String testSessionAttributes(Model model){
//		System.out.println("sessionAttributes执行了");
//		//底层会存储到request域对象中
//		model.addAttribute("meimei","e");
//
//		return "quickStart";
//
//	}


}
