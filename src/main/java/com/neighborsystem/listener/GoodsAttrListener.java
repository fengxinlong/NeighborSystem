package com.neighborsystem.listener;

import com.alibaba.fastjson.JSON;
import com.neighborsystem.entity.Evaluate;
import com.neighborsystem.entity.Goods;
import com.neighborsystem.entity.GoodsType;
import com.neighborsystem.entity.Memory;
import com.neighborsystem.service.IEvaluateService;
import com.neighborsystem.service.IGoodsService;
import com.neighborsystem.service.IGoodsTypeService;
import com.neighborsystem.service.IMemoryService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

public class GoodsAttrListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ApplicationContext app=new ClassPathXmlApplicationContext("applicationContext.xml");
		IGoodsTypeService typeService = app.getBean(IGoodsTypeService.class);
		ServletContext application = arg0.getServletContext();
		IMemoryService memoryService = app.getBean(IMemoryService.class);
		List<GoodsType> typeList = typeService.findAllType();
		application.setAttribute("goodsTypeList", typeList);
		List<Memory> memoryList = memoryService.finAllMemory();
		application.setAttribute("memoryList", memoryList);
		IGoodsService service = app.getBean(IGoodsService.class);
		List<Goods> goodsList = service.findAll();
		JedisPool jedisPool = app.getBean(JedisPool.class);
		IEvaluateService evaService = app.getBean(IEvaluateService.class);
		Jedis jedis = jedisPool.getResource();
		for (Goods g : goodsList) {
			List<Evaluate> evaList = evaService.findEvaluateByGoodsId(g.getGoodsId());
			g.setEvaList(evaList);
			StringBuffer str =new StringBuffer(JSON.toJSONString(g));
			jedis.set(g.getGoodsId()+"", str.toString());
		}
	}

}
