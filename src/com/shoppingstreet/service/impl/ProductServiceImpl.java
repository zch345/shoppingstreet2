package com.shoppingstreet.service.impl;

import java.sql.Connection;
import java.util.List;

import com.shoppingstreet.dao.ProductDao;
import com.shoppingstreet.dao.impl.ProductDaoImpl;
import com.shoppingstreet.entity.Product;
import com.shoppingstreet.service.ProductService;
import com.shoppingstreet.utils.DataSourceUtil;

public class ProductServiceImpl implements ProductService {

	@Override
	public List<Product> getProductList(String proName) {
		Connection connection = null;
		List<Product> productList=null;
		try {
			connection = DataSourceUtil.openConnection();
			ProductDao productDao = new ProductDaoImpl(connection);
			productList=productDao.getProductByName(proName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
			return productList;
		}
	}
	@Override
	public Product getProductById(Integer productId) {
		Connection connection = null;
		Product product=null;
		try {
			connection = DataSourceUtil.openConnection();
			ProductDao productDao = new ProductDaoImpl(connection);
			product=productDao.getProductById(productId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtil.closeConnection(connection);
			return product;
		}
	}
}
