package com.waimai.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.waimai.dao.UserDao;
import com.waimai.model.User;
import com.waimai.util.DBHelper;

public class UserImpl implements UserDao{
	private QueryRunner runner;
	private Connection connection;
	public UserImpl() {
		// TODO Auto-generated constructor stub
		runner = new QueryRunner();
		connection = DBHelper.getConnection();
	}
	
	
	
	
	
	public User findByUserName(String userName) {
		// TODO Auto-generated method stub
		String sql = "select * from users where username = ?";
		ResultSetHandler<User> rsHandler = new BeanHandler<User>(User.class);
		User user = null;
		try {
			user = runner.query(connection,sql, rsHandler,userName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	

	public boolean addUser(User user) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into users(userid,username,password,nickname,location,sex,headimgurl,phone,money) "
				+ "values(?,?,?,?,?,?,?,?,?)";

		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.execute("set Names utf8");
			stmt.setInt(1, user.getUserid());
			stmt.setString(2, user.getUsername());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getNickname());
			stmt.setString(5, user.getLocation());
			stmt.setInt(6, user.getSex());
			stmt.setString(7, user.getHeadimgurl());
			stmt.setString(8, user.getPhone());
			stmt.setInt(9, user.getMoney());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
					conn = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	public boolean isExist(String userName){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from users where username = ?";

		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.execute("set Names utf8");
			stmt.setString(1, userName);
			rs = stmt.executeQuery();
			if (rs.next()) {
				System.out.println("此用户已存在");
				return true;
			}else{
				System.out.println("此用户不存在");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
					
			if (rs != null) {
				try {
				    rs.close();
					rs = null;
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
					conn = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


}
