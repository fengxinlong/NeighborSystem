package com.neighborsystem.mapper;

import com.neighborsystem.entity.Cart;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartMapper {
	Cart findCartById(Integer id);
	Integer addCart(Cart cart);
	Integer deleteCart(Integer cartId);
	Integer updateCart(Cart cart);
	List<Cart> findCartListByUserId(Integer userId);
	Cart findCartByUserId(Integer userId, Integer goodsId);
}
