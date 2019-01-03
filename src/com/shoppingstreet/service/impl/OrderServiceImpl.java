package com.shoppingstreet.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import com.shoppingstreet.dao.OrderDao;
import com.shoppingstreet.dao.OrderDetailDao;
import com.shoppingstreet.dao.ProductDao;
import com.shoppingstreet.dao.impl.OrderDaoImpl;
import com.shoppingstreet.dao.impl.OrderDetailDaoImpl;
import com.shoppingstreet.dao.impl.ProductDaoImpl;
import com.shoppingstreet.entity.Order;
import com.shoppingstreet.entity.OrderDetail;
import com.shoppingstreet.entity.Product;
import com.shoppingstreet.entity.User;
import com.shoppingstreet.service.OrderService;
import com.shoppingstreet.utils.DataSourceUtil;
import com.shoppingstreet.utils.StringUtils;


public class OrderServiceImpl  implements OrderService {

    /**
     * 结算购物车物品包含以下步骤：
     * 1.生成订单
     * 2.生成订单明细
     * 注意加入事物的控制
     */

    @Override
    public Order payShoppingCart(Map<Product, Integer> car, double totalPrice, User user, String address) {
        // TODO Auto-generated method stub
        Connection connection = null;
        Order order = new Order();
        try {
            connection = DataSourceUtil.openConnection();
            connection.setAutoCommit(false);
            OrderDao orderDao = new OrderDaoImpl(connection);
            OrderDetailDao orderDetailDao = new OrderDetailDaoImpl(connection);
            //增加订单
            order.setUserId(user.getId());
            order.setLoginName(user.getLoginName());
            order.setUserAddress(address);
            order.setCreateTime(new Date());
            order.setCost(totalPrice);
            order.setSerialNumber(StringUtils.randomUUID());
            orderDao.add(order);
            //增加订单对应的明细信息
            for (Product product : car.keySet()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderId(order.getId());
                orderDetail.setCost(product.getPrice());
                orderDetail.setProduct(product);
                orderDetail.setQuantity(car.get(product));
                orderDetailDao.add(orderDetail);
                connection.commit();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            order = null;
        } finally {
            try {
                connection.setAutoCommit(true);
                DataSourceUtil.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return order;
    }
}
