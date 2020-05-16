package com.neighborsystem.mapper;

import com.neighborsystem.entity.Goods;
import com.neighborsystem.entity.GoodsVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsMapper {
	List<Goods> findAllBaseData();
	List<Goods> findAll();
	Integer saveGoods(Goods goods);
	Integer deleteGoods(Integer goods);
	Integer updateGoods(Goods goods);
	Goods findGoodsById(Integer id);
	List<Goods> findGoodsByType(Integer typeId);
	List<Goods> findHotGoods(Integer num);
	List<Goods> findGoodsLikeName(String name);
	List<Goods> findGoodsByVolume(Integer limit);

	Goods findAllDonation();

	/*
	* 根据传入参数条件 查询条件
	* */
	List<Goods> findUserByCondition(Goods goods);
	/*
	* 范围
	* */
	List<Goods> findGoodsInIds(GoodsVo goodsVo);


}
