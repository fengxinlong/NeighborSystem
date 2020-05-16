package com.dao.test;

import com.neighborsystem.entity.Goods;
import com.neighborsystem.mapper.GoodsMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DaoTest {
	private ApplicationContext ac;

	@Before
	public void init(){
		ac=new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	@Test
	public void testFindAllBaseData(){
		GoodsMapper dao = ac.getBean(GoodsMapper.class);
		List<Goods> goods = dao.findAllBaseData();
		for(Goods good:goods){
			System.out.println(good.toString());
		}
	}
/*
* 基于注解查询
*
* */
//	@Test
//	public void testFindAll() throws Exception {
//		//加载配置文件
//		InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
//
//		//创建SqlSessionFactory对象
//		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
//		//创建SqlSession对象
//		SqlSession session = factory.openSession();
//		//获取到代理对象
//		GoodsMapper dao = session.getMapper(GoodsMapper.class);
//		//查询所有数据
//		dao.findAll();
//		session.close();
//		in.close();
//
//
//	}
}
