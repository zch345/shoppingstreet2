package com.shoppingstreet.dao;

import com.shoppingstreet.entity.User;

public interface UserDao extends IBaseDao{
	int add(User user) throws Exception;//�����û���Ϣ
	User getLoginUser(String loginName,String password);
}
