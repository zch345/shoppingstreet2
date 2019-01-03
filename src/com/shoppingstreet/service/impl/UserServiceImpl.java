package com.shoppingstreet.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.shoppingstreet.dao.UserDao;
import com.shoppingstreet.dao.impl.UserDaoImpl;
import com.shoppingstreet.entity.User;
import com.shoppingstreet.service.UserService;
import com.shoppingstreet.utils.DataSourceUtil;

public class UserServiceImpl implements UserService {
	@Override
	public boolean add(User user){
		Connection connection = null;
		Integer count=0;
		try {
			connection = DataSourceUtil.openConnection();
			UserDao userDao = new UserDaoImpl(connection);
			count=userDao.add(user);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DataSourceUtil.closeConnection(connection);
			return  count>0;
		}
	}

	@Override
	public User getLoginUser(String loginName, String password) {
		Connection connection = null;
		User user = null;
		try {
			connection = DataSourceUtil.openConnection();
			UserDao userDao = new UserDaoImpl(connection);
			user=userDao.getLoginUser(loginName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DataSourceUtil.closeConnection(connection);
			return  user;
		}
	}
}
