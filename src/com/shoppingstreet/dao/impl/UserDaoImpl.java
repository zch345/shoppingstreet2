package com.shoppingstreet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.shoppingstreet.dao.UserDao;
import com.shoppingstreet.entity.User;

/**
 * 用户dao
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    public UserDaoImpl(Connection connection) {
        super(connection);
    }
	
    @Override
    public User tableToClass(ResultSet rs) throws Exception {
    	User user = new User();
        user.setLoginName(rs.getString("loginName"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setMobile(rs.getString("mobile"));
        user.setId(rs.getInt("id"));
        return user;
    }
    /**
     * 保存用户
     *
     * @param user
     * @throws java.sql.SQLException
     */
    public int add(User user){//新增用户信息
    	Integer id=0;
    	try {
    		String sql=" insert into user(loginName,password,email,mobile) values(?,?,?,?) ";
            try {
                Object param[]=new Object[]{user.getLoginName(),user.getPassword(),user.getEmail(),user.getMobile()};
                id=this.executeInsert(sql,param);
                user.setId(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	this.closeResource();
        }
    	return id;
    }

	@Override
	public User getLoginUser(String loginName, String password) {
		String sql = "select id,loginName,password,email,mobile from user where loginName=? and password=?";
		
		ResultSet resultSet = this.executeQuery(sql.toString(),new String[]{loginName,password});
		User user=null;
		try {
			if(resultSet.next()){
				user = this.tableToClass(resultSet);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			this.closeResource();
			this.closeResource(resultSet);
		}
		
		return user;
	}

}
