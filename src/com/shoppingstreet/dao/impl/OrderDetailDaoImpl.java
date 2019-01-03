package com.shoppingstreet.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.shoppingstreet.dao.OrderDetailDao;
import com.shoppingstreet.dao.ProductDao;
import com.shoppingstreet.entity.OrderDetail;
import com.shoppingstreet.entity.Product;

public class OrderDetailDaoImpl  extends BaseDaoImpl implements OrderDetailDao{

    public OrderDetailDaoImpl(Connection connection) {
        super(connection);
    }
    

    @Override
    public OrderDetail tableToClass(ResultSet rs) throws Exception {
        OrderDetail orderDetail = new OrderDetail();
//        orderDetail.setId(rs.getInt("id"));
//        orderDetail.setOrderId(rs.getInt("orderId"));
//        orderDetail.setProduct((Product) productDao.getProductById(rs.getInt("productId")));
//        orderDetail.setProductId(rs.getInt("productId"));
//        orderDetail.setQuantity(rs.getInt("quantity"));
//        orderDetail.setCost(rs.getFloat("cost"));
        return orderDetail;
    }

    public void add(OrderDetail detail) throws SQLException {//±£¥Ê∂©µ•œÍ«È
        Integer id=0;
		String sql=" insert into order_detail(orderId,productId,quantity,cost) values(?,?,?,?) ";
        try {
            Object param[]=new Object[]{detail.getOrderId(),detail.getProduct().getId(),detail.getQuantity(),detail.getCost()};
            id=this.executeInsert(sql,param);
            detail.setId(id);
        } catch (Exception e) {
			this.closeResource();
            e.printStackTrace();
        }
    }
}
