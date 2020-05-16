package com.neighborsystem.service;

import com.neighborsystem.entity.Cart;

import java.util.List;

public interface ICartService {
	Integer addGoodsToCart(Cart cart);
	Cart findCartByUserId(Integer userId, Integer goodsId);
	Integer updateCart(Cart cart);
	List<Cart> findCartByUserId(Integer userId);
	Integer deleteCart(Integer cartId);
	Cart findCartById(Integer cartId);
}
