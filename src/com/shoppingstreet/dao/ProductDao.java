package com.shoppingstreet.dao;

import java.util.List;

import com.shoppingstreet.entity.Product;

public interface ProductDao extends IBaseDao {
	public List<Product> getProductByName(String proName)throws Exception;
	public Product getProductById(Integer id)throws Exception;
}
