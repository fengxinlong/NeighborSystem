package com.neighborsystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neighborsystem.entity.Evaimg;
import com.neighborsystem.entity.Evaluate;
import com.neighborsystem.mapper.EvaimgMapper;
import com.neighborsystem.mapper.EvaluateMapper;
import com.neighborsystem.service.IEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/*
* <bean id="EvaluateService" class="com....EvaluateServiceImpl"
*		scope="" init-method="" destroy-method="">
* 用于创建对象的注解
* 	 他们的作用就和在xml配置文件中编写一个<bean标签>实现的功能是一样的
* 用于注入数据的
* 	他们的作用就和在xml配置文件中的bean标签中写一个<property>标签的作用是一样的
* 用于改变作用范围的
* 	他们的作用就和在bean标签中使用scope属性实现的功能相关
* 和生命周期相关的
* 	他们的作用就和在bean标签中使用init-method 和destroy-method作用是一样的
* */
@Service
public class EvaluateServiceImpl implements IEvaluateService {
	@Autowired
	private EvaluateMapper evaluateMapper;
	@Autowired
	private EvaimgMapper evaImgMapper;
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Integer addEvaluate(Evaluate eva,String[] imgs) {
		Integer rs = evaluateMapper.addEvaluate(eva);
		if(rs>0){
			for (String img : imgs) {
				Evaimg evaimg=new Evaimg(img, eva.getEvaId());
				evaImgMapper.addEvaimg(evaimg);
			}
		}
		return rs;
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	@Override
	public List<Evaluate> findEvaluateByGoodsId(Integer goodsId) {
		List<Evaluate> evaList = evaluateMapper.findEvaByGoodsId(goodsId);
		for (Evaluate evaluate : evaList) {
			List<Evaimg> imgList = evaImgMapper.findEvaimgByEvaId(evaluate.getEvaId());
			evaluate.setImgList(imgList);
		}
		return evaList;
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	@Override
	public PageInfo<Evaluate> findAllEvaluate(Integer page,Integer limit,String keyword) {
		PageHelper.startPage(page, limit);
		List<Evaluate> evaList=new ArrayList<Evaluate>();
		if(keyword!=null&&!keyword.trim().equals("")){
			evaList=evaluateMapper.findAllEvaluteLikeContent(keyword);
		}else{
			evaList = evaluateMapper.findAllEvalute();
		}
		PageInfo<Evaluate> info=new PageInfo<Evaluate>(evaList);
		return info;
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Integer deleteEvaluate(Integer evaId) {
		return  evaluateMapper.deleteEvaluate(evaId);
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	@Override
	public Evaluate findEvaluteById(Integer evaId) {
		Evaluate eva = evaluateMapper.findEvaById(evaId);
		List<Evaimg> imgList = evaImgMapper.findEvaimgByEvaId(eva.getEvaId());
		eva.setImgList(imgList);
		return eva;
	}

}
