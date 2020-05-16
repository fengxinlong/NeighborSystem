package com.neighborsystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neighborsystem.entity.Goods;
import com.neighborsystem.entity.GoodsType;
import com.neighborsystem.mapper.GoodsMapper;
import com.neighborsystem.mapper.GoodsTypeMapper;
import com.neighborsystem.service.IGoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("goodsTypeService")
public class GoodsTypeServiceImpl implements IGoodsTypeService {

	public GoodsTypeServiceImpl(){
		System.out.println("对象创建了");
	}


	@Autowired
	private GoodsTypeMapper goodsTypeMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	@Override
	public List<GoodsType> findAllType() {
		List<GoodsType> typeList = goodsTypeMapper.findAllType();
		for (GoodsType goodsType : typeList) {
			List<Goods> goodsList = goodsMapper.findGoodsByType(goodsType.getTypeId());
			goodsType.setGoodsList(goodsList);
		}
		return typeList;
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	@Override
	public PageInfo<GoodsType> findTypeBySplitPage(Integer page, Integer limit, String keyword) {
		PageHelper.startPage(page, limit);
		List<GoodsType> list=new ArrayList<GoodsType>();
 		if(keyword!=null&&!keyword.trim().equals("")){
			list=goodsTypeMapper.findAllTypeByLikeName(keyword);
		}else{
			list=goodsTypeMapper.findAllBySplitPage();
		}
 		PageInfo<GoodsType> info=new PageInfo<GoodsType>(list);
 		return info;
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Integer updateGoodsType(GoodsType type) {
		return goodsTypeMapper.updateGoodsType(type);
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Integer addGoodsType(GoodsType type) {
		return goodsTypeMapper.saveGoodsType(type);
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Integer changeTypeState(Integer state,Integer typeId) {
		return goodsTypeMapper.changeTypeState(state, typeId);
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Integer deleteGoodsType(Integer typeId) {
		return goodsTypeMapper.deleteGoodsType(typeId);
	}

}
