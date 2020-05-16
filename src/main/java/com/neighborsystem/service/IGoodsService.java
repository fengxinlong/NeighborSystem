package com.neighborsystem.service;

import com.github.pagehelper.PageInfo;
import com.neighborsystem.entity.Goods;

import java.util.List;

public interface IGoodsService {
	List<Goods> findAll();
	Goods findById(Integer id);
	Integer update(Goods goods);
	Integer deleteGoods(Integer goodsId);
	PageInfo<Goods> findBySplitPage(Integer page, Integer size, String keyword);
	List<Goods> findGoodsByType(Integer typeId);
	List<Goods> findHotGoods(Integer num);
	List<Goods> findGoodsLikeName(String name);
	Integer addGoods(Goods goods);
	List<Goods> findGoodsByVolume(Integer limit);
	Goods findAllDonations();
}
