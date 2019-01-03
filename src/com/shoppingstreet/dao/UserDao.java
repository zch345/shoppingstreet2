package com.shoppingstreet.dao;

import com.shoppingstreet.entity.User;

public interface UserDao extends IBaseDao{
	int add(User user) throws Exception;//新增用户信息
	User getLoginUser(String loginName,String password);
}
