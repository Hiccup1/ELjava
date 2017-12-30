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

import com.waimai.dao.OrderDao;
import com.waimai.model.Order;
import com.waimai.model.OrderItem;
import com.waimai.util.DBHelper;

public class OrderImpl implements OrderDao{
	private QueryRunner runner;
	private Connection connection;
	public OrderImpl(){
		// TODO Auto-generated constructor stub
		runner = new QueryRunner();
		connection = DBHelper.getConnection();
	}
	
	public Order findById(int oid) {
		// TODO Auto-generated method stub
		String sql = "select * from orders where oid = ?";
		ResultSetHandler<Order> rsHandler = new BeanHandler<Order>(Order.class);
		Order order  = null;
		try {
			order = runner.query(connection,sql, rsHandler,oid);
		} catch (Exception e) {
			// TODO: handle exception	
			e.printStackTrace();
		}
		return order;
	}
	
	public List<Order> findOrdersByUid(int uid) {
		String sql = "select * from orders where uid = ? and state != 3 order by oid DESC";
		ResultSetHandler<List<Order>> rsHandler = new BeanListHandler<Order>(Order.class);
		List<Order> orders  = null;
		try {
			orders = runner.query(connection,sql, rsHandler,uid);
		} catch (Exception e) {
			// TODO: handle exception	
			e.printStackTrace();
		}
		return orders;
	}
	
	public List<Order> ShowDoingOrders(int sid) {
		String sql = "select * from orders where state = 1 and sid = ? order by oid DESC";
		ResultSetHandler<List<Order>> rsHandler = new BeanListHandler<Order>(Order.class);
		List<Order> orders  = null;
		try {
			orders = runner.query(connection,sql, rsHandler, sid);
		} catch (Exception e) {
			// TODO: handle exception	
			e.printStackTrace();
		}
		return orders;
	}
	
	public List<Order> ShowDoneOrders(int sid) {
		String sql = "select * from orders where state = 2 and sid = ? order by oid DESC";
		ResultSetHandler<List<Order>> rsHandler = new BeanListHandler<Order>(Order.class);
		List<Order> orders  = null;
		try {
			orders = runner.query(connection,sql, rsHandler, sid);
		} catch (Exception e) {
			// TODO: handle exception	
			e.printStackTrace();
		}
		return orders;
	}

	public List<OrderItem> getOrderItem(int oid){
		String sql = "select * from orderitem where oid =?";
		ResultSetHandler<List<OrderItem>> rSetHandler = new BeanListHandler<OrderItem>(OrderItem.class);
		List<OrderItem> items = null;
		try {
			items = runner.query(connection,sql, rSetHandler,oid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return items;
	}

	
	
	public boolean addOrder(Order order) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "insert into orders(oid,sid,uid,time,location,price,state,pay,discount,remark,name,wlocation,phone,sex) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, order.getOid());
			stmt.setInt(2, order.getSid());
			stmt.setInt(3, order.getUid());
			stmt.setString(4, order.getTime());
			stmt.setString(5, order.getLocation());
			stmt.setFloat(6, (float)order.getPrice());
			stmt.setInt(7, order.getState());
			stmt.setInt(8, order.getPay());
			stmt.setDouble(9, order.getDiscount());
			stmt.setString(10, order.getRemark());
			stmt.setString(11, order.getName());
			stmt.setString(12, order.getWlocation());
			stmt.setString(13, order.getPhone());
			stmt.setInt(14, order.getSex());
			stmt.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			
			return false;
		}finally {
			//
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

	
	public boolean delOrder(int oid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		// TODO Auto-generated method stub
		String sql="update orders set state=3 where oid=?";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.execute("set Names utf8");
			stmt.setInt(1, oid);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
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
		
		return false;
	}
	
	
	public boolean redelOrder(int oid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		// TODO Auto-generated method stub
		String sql="update orders set remark='1' where oid=?";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.execute("set Names utf8");
			stmt.setInt(1, oid);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
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
		
		return false;
	}

	
	public boolean modOrder(Order order) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement=null;
		String sql="update orders set state=? where oid=?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, order.getSid());
			preparedStatement.setInt(2, order.getOid());
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
