package com.shoppingstreet.service;

import java.util.Map;

import com.shoppingstreet.entity.Order;
import com.shoppingstreet.entity.Product;
import com.shoppingstreet.entity.User;

public interface OrderService {
	// ���㶩�����������ͣ�Order���󣬲��������ﳵ���ܼ۸�User�����ջ���ַ����
	public Order payShoppingCart(Map<Product,Integer> car,double totalPrice, User user,String address);
}
