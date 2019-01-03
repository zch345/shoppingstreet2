package com.shoppingstreet.service;

import java.util.Map;

import com.shoppingstreet.entity.Order;
import com.shoppingstreet.entity.Product;
import com.shoppingstreet.entity.User;

public interface OrderService {
	// 结算订单（返回类型：Order对象，参数：购物车、总价格、User对象、收货地址）。
	public Order payShoppingCart(Map<Product,Integer> car,double totalPrice, User user,String address);
}
