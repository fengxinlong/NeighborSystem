package com.neighborsystem.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.neighborsystem.entity.Goods;
import com.neighborsystem.entity.Guess;
import com.neighborsystem.entity.Users;
import com.neighborsystem.service.IGoodsService;
import com.neighborsystem.service.IGuessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/guess")
public class GuessController {
	@Autowired
	private IGuessService guessService;
	@Autowired
	private IGoodsService goodsService;
	
	@RequestMapping("findFavorite")
	@ResponseBody
	public JSONObject findFavoriteGoods(Model model,Integer page,HttpServletRequest request){
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		PageInfo<Guess> info = guessService.findAllFavoriteByUserId(user.getUserId(), page, 8);
		JSONObject obj=new JSONObject();
		obj.put("data",info.getList());
		obj.put("count", info.getTotal());
		return obj;
	}
	
	@RequestMapping("addToFavorite")
	@ResponseBody
	public String addGoodsToFavorite(Integer goodsId,HttpServletRequest request){
		HttpSession session = request.getSession();
		Users user=(Users) session.getAttribute("user");
		Integer rs = guessService.addGoodsToFavorite(user.getUserId(), goodsId);
		if(rs>0){
			return "success";
		}else{
			return "fail";
		}
	}
	
	@RequestMapping("isFaorite")
	@ResponseBody
	public String isFavorite(Integer goodsId,HttpServletRequest request){
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		if(user!=null){
			Guess guess = guessService.findGuessByUserId(user.getUserId(), goodsId);
			Integer rs = guess.getFavorite();
			if(rs>0){
				return "true";
			}else{
				return "false";
			}
		}else{
			return "false";
		}
	}
	@RequestMapping("removeFavorite")
	@ResponseBody
	public String removeFavorite(Integer goodsId,HttpServletRequest request){
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		Integer rs = guessService.removeFavorite(user.getUserId(), goodsId);
		if(rs>0){
			return "success";
		}else{
			return "fail";
		}
	}
	@RequestMapping("findRecentGoods")
	@ResponseBody
	public List<Guess> findRecentViewGoods(HttpServletRequest request){
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		List<Guess> list = guessService.findRecentViewGoods(user.getUserId(), 8);
		return list;
	}
	@RequestMapping("findMostHotGoods")
	@ResponseBody
	public JSONArray findHotGoods(){
		List<Map<String, Object>> list = guessService.findMostHotGoods(6);
		JSONArray arr=new JSONArray();
		for (Map<String, Object> map : list) {
			JSONObject obj=new JSONObject();
			Integer goodsId = (Integer) map.get("g");
			Number n=(Number) map.get("num");
			Goods g = goodsService.findById(goodsId);
			obj.put("name", g.getGoodsName());
			obj.put("num", n.longValue());
			arr.add(obj);
		}
		return arr;
	}
}
