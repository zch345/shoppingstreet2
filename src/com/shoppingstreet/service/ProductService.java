package com.shoppingstreet.service;

import java.util.List;

import com.shoppingstreet.entity.Product;

public interface ProductService {
	public List<Product> getProductList(String proName);
	public Product getProductById(Integer productId);
}
