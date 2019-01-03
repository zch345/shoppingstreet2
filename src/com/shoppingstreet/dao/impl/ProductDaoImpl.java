package com.shoppingstreet.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shoppingstreet.dao.ProductDao;
import com.shoppingstreet.entity.Product;

public class ProductDaoImpl extends BaseDaoImpl implements ProductDao {

    public ProductDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Product tableToClass(ResultSet rs) throws Exception {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getFloat("price"));
        product.setStock(rs.getInt("stock"));
        product.setCategoryLevel1Id(rs.getInt("categoryLevel1Id"));
        product.setCategoryLevel2Id(rs.getInt("categoryLevel2Id"));
        product.setCategoryLevel3Id(rs.getInt("categoryLevel3Id"));
        product.setFileName(rs.getString("fileName"));
        return product;
    }

	@Override
	public List<Product> getProductByName(String proName) throws Exception {
		List<Object> paramsList=new ArrayList<Object>();   
		List<Product> productList=new ArrayList<Product>();
		StringBuffer sql=new StringBuffer("  select id,name,description,price,stock,categoryLevel1Id,categoryLevel2Id,categoryLevel3Id,fileName,isDelete from product  where name like ? ");
		ResultSet resultSet = null;
		try{
			paramsList.add("%"+proName+"%");
			resultSet=this.executeQuery(sql.toString(),paramsList.toArray());
			while (resultSet.next()) {
				Product product = this.tableToClass(resultSet);
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource(resultSet);
			this.closeResource();
		}
		return productList;
	}
	
	@Override
	public Product getProductById(Integer id) throws Exception {
		String sql = " select id,name,description,price,stock,categoryLevel1Id,categoryLevel2Id,categoryLevel3Id,fileName,isDelete from product where id = ? ";
		ResultSet resultSet = null;
		Product product = null;
		try {
			Object params[] = new Object[] { id };
			resultSet = this.executeQuery(sql, params);
			while (resultSet.next()) {
				product = tableToClass(resultSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeResource(resultSet);
			this.closeResource();
			return product;
		}
	}
}
