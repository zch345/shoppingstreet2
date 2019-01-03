package com.shoppingstreet.dao;

import com.shoppingstreet.entity.OrderDetail;

public interface OrderDetailDao extends IBaseDao {
    public void add(OrderDetail detail) throws Exception;
}
