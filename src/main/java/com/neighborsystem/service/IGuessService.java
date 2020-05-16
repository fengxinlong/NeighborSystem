package com.neighborsystem.service;

import com.github.pagehelper.PageInfo;
import com.neighborsystem.entity.Guess;

import java.util.List;
import java.util.Map;

public interface IGuessService {
	List<Guess> findGuessGoodsByUserId(Integer userId, Integer num);
	Integer addGuess(Guess guess);
	Guess findGuessByUserId(Integer userId, Integer goodsId);
	Integer updateGuess(Guess g);
	Integer addGoodsToFavorite(Integer userId, Integer goodsId);
	PageInfo<Guess> findAllFavoriteByUserId(Integer userId, Integer page, Integer limit);
	Integer removeFavorite(Integer userId, Integer goodsId);
	List<Guess> findRecentViewGoods(Integer userId, Integer limit);
	List<Map<String,Object>> findMostHotGoods(Integer limit);
}
