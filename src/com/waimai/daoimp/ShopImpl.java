package com.waimai.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.waimai.dao.ShopDao;
import com.waimai.model.Dish;
import com.waimai.model.Shop;
import com.waimai.util.DBHelper;

public class ShopImpl implements ShopDao{
	private QueryRunner runner;
	private Connection connection;
	private String sql;
	private PreparedStatement preparedStatement;
	public ShopImpl() {
		// TODO Auto-generated constructor stub
		runner = new QueryRunner();
		connection = DBHelper.getConnection();
	}
	
	public List<Shop> showAllShops() {
		// TODO Auto-generated method stub
		String sql = "select * from shops";
		ResultSetHandler<List<Shop>> rSetHandler = new BeanListHandler<Shop>(Shop.class);
		List<Shop> shops = null;
		try {
			shops = runner.query(connection,sql, rSetHandler);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shops;
	}
	
	public Shop findBySid(int sid) {
		// TODO Auto-generated method stub
		
		String sql = "select * from shops where sid=?";
		ResultSetHandler<Shop> rsHandler = new BeanHandler<Shop>(Shop.class);
		Shop shop  = null;
		try {
			shop = runner.query(connection,sql, rsHandler,sid);
		} catch (Exception e) {
			// TODO: handle exception	
			e.printStackTrace();
		}
		return shop;
	}
	
	public Shop findByUid(int uid) {
		// TODO Auto-generated method stub
		
		String sql = "select * from shops where uid=?";
		ResultSetHandler<Shop> rsHandler = new BeanHandler<Shop>(Shop.class);
		Shop shop  = null;
		try {
			shop = runner.query(connection,sql, rsHandler,uid);
		} catch (Exception e) {
			// TODO: handle exception	
			e.printStackTrace();
		}
		return shop;
	}

	
	public boolean addShop(Shop shop) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		if(shop!=null){
			String sql= "insert into shops(uid,name,img,phone,location,remark) "
					+ "values(?,?,?,?,?,?)";
			try{
				conn = DBHelper.getConnection();
				stmt = conn.prepareStatement(sql);
				stmt.execute("set Names utf8");
				stmt.setInt(1, shop.getUid());
				stmt.setString(2, shop.getName());
				stmt.setString(3, shop.getImg());
				stmt.setString(4, shop.getPhone());
				stmt.setString(5, shop.getLocation());
				stmt.setString(6, shop.getRemark());
				stmt.executeUpdate();
				return true;
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
			return false;
	}

	
	public boolean modifyShop(Shop shop) {
		// TODO Auto-generated method stub
		sql="update shops set name=?,img=?,phone=?,location=?,remark=? where sid=?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(2, shop.getImg());
			preparedStatement.setString(1, shop.getName());
			preparedStatement.setString(3, shop.getPhone());
			preparedStatement.setString(4, shop.getLocation());
			preparedStatement.setString(5, shop.getRemark());
			preparedStatement.setInt(6, shop.getSid());
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void closeConnection() {
		try {
			DbUtils.close(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
