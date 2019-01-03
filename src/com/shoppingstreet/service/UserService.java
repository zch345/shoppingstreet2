package com.shoppingstreet.service;

import com.shoppingstreet.entity.User;

public interface UserService {
	public boolean add(User user);
	public User getLoginUser(String loginName,String password);
}
