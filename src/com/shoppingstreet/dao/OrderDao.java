package com.shoppingstreet.dao;

import com.shoppingstreet.entity.Order;

public interface OrderDao extends IBaseDao {
	public void add(Order order) ;
}
