package com.neighborsystem.controller;

import com.neighborsystem.entity.TestUsers;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

/*
*
* 模拟异步响应
* */
@Controller
@RequestMapping("/json")
public class JsonController {

	@RequestMapping("/testAjax")
	public @ResponseBody TestUsers testJson(@RequestBody TestUsers user){
		System.out.println("testAjax执行了");
		//客户端发送ajax请求，传的是json字符串,后端已经把json字符串封装到user对象中
		System.out.println(user);
		//做响应,模拟查询数据库

		user.setUname("xixi");
		user.setUage("40");
		user.setUbirthday("2020-5-14");

		//将发过来的json封装到一个JavaBean对象中
		return user;
		//和属性相同直接封装  //额外jar包 jackson 串转成对象或者对象转成串
	}
	@RequestMapping("/index")
	public String toJson(){
		return "response";
	}

	/*
	* 文件上传
	* */
	@RequestMapping("testFileUpload1")
	public String fileUpload1(HttpServletRequest request) throws Exception {
		System.out.println("文件上传");


		//使用fileupload组件完成文件上传
		//上传的位置
		String path = request.getSession().getServletContext().getRealPath("/uploads/");
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}

		//解析request对象,获取上传文件项
		DiskFileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload upload=new ServletFileUpload(factory);
		
		//解析:
		List<FileItem> items = upload.parseRequest(request);
		//遍历
		for(FileItem item:items){
			//进行判断:当前item对象是否是上传文件项
			if(item.isFormField()){
				//普通表单项
				System.out.println("hehe");
			}else{
				//上传文件项目
				//获取上传文件的名称
				String fileName = item.getName();
				//完成文件上传
				String uuid = UUID.randomUUID().toString().replace("-", "");
				fileName=uuid+"_"+fileName;
				item.write(new File(path,fileName));
				System.out.println(fileName);
				//删除临时文件
				item.delete();
			}
		}

		return "success";
	}

	@RequestMapping("testFileUpload2")
	public String testFileUpload2(MultipartFile upload,HttpServletRequest request) throws Exception {
		String path=request.getSession().getServletContext().getRealPath("/uploads/");
		//SpringMVC上传
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		//传统名字
		String fileName = upload.getOriginalFilename();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		fileName=uuid+"_"+fileName;

		upload.transferTo(new File(path,fileName));

		return "success";
	}
/*

跨域上传
*/

	@RequestMapping("testFileUpload3")
	public String testFileUpload3(MultipartFile upload) throws Exception {
		//定义上传服务器路径
		String path="http://localhost:9090/uploads";


		String fileName=upload.getOriginalFilename();
		String uuid=UUID.randomUUID().toString().replace("-","");
		fileName=fileName+uuid;
		//和图片服务器进行连接
//		Client client =Client.creat();
//		WebResource webResource=client.resource(path+"/"+fileName);

		//上传文件
//		webResource.put(upload.getBytes());


		/*
		* 跨服务器上传
		* */

		return "success";

	}

}
