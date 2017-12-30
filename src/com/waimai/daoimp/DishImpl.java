package com.waimai.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;


import com.waimai.dao.DishDao;
import com.waimai.model.Dish;
import com.waimai.util.DBHelper;

public class DishImpl implements DishDao{
	private QueryRunner runner;
	private Connection connection;
	private String sql=null;
	private PreparedStatement pStatement=null;
	public DishImpl(){
		// TODO Auto-generated constructor stub
		runner = new QueryRunner();
		connection = DBHelper.getConnection();
	}
	public Dish findById(int did) {
		// TODO Auto-generated method stub
		sql = "select * from dishes where did = ?";
		ResultSetHandler<Dish> rsHandler = new BeanHandler<Dish>(Dish.class);
		Dish dish  = null;
		try {
			dish = runner.query(connection,sql, rsHandler,did);
		} catch (Exception e) {
			// TODO: handle exception	
			e.printStackTrace();
		}
		return dish;
	}

	public boolean addDish(Dish dish) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into dishes(sid,name,price,remark,img,sellcount,tid) "
				+ "values(?,?,?,?,?,?,?)";

		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.execute("set Names utf8");
			stmt.setInt(1, dish.getSid());
			stmt.setString(2, dish.getName());
			stmt.setDouble(3, dish.getPrice());
			stmt.setString(4, dish.getRemark());
			stmt.setString(5, dish.getImg());
			stmt.setInt(6, dish.getSellcount());
			stmt.setInt(7, dish.getTid());
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

	public boolean delDish(int did) {
		// TODO Auto-generated method stub
		sql="update dishes set remark = '0' where `did` = ? ";
		try {
			pStatement=connection.prepareStatement(sql);
			pStatement.setInt(1, did);
			pStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean modDish(Dish dish) {
		// TODO Auto-generated method stub
		if(dish!=null){
			sql="UPDATE `dishes` SET `name` = ? ,`price` = ? ,`img` = ? WHERE `did` = ?";
			try {
				pStatement=connection.prepareStatement(sql);
				pStatement.setString(1,dish.getName());
				pStatement.setDouble(2, dish.getPrice());
				pStatement.setString(3, dish.getImg());
				pStatement.setInt(4, Integer.valueOf(dish.getDid()));
				pStatement.executeUpdate();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	public List<Dish> findByType(int tid) {
		// TODO Auto-generated method stub
		sql = "select * from dishes where tid = ?";
		ResultSetHandler<List<Dish>> rSetHandler = new BeanListHandler<Dish>(Dish.class);
		List<Dish> dishs = null;
		try {
			dishs = runner.query(connection,sql, rSetHandler,tid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dishs;
	}
	
	public List<Dish> findByShopsid(int sid) {
		// TODO Auto-generated method stub
		sql = "select * from dishes where sid = ? order by tid";
		ResultSetHandler<List<Dish>> rSetHandler = new BeanListHandler<Dish>(Dish.class);
		List<Dish> dishs = null;
		try {
			dishs = runner.query(connection,sql, rSetHandler,sid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dishs;
	}
	
	public Dish findByName(String name) {
		// TODO Auto-generated method stub
		sql = "select * from dishes where name = ?";
		ResultSetHandler<Dish> rsHandler = new BeanHandler<Dish>(Dish.class);
		Dish dish  = null;
		try {
			dish = runner.query(connection,sql, rsHandler,name);
		} catch (Exception e) {
			// TODO: handle exception	
			e.printStackTrace();
		}
		return dish;
	}
}
