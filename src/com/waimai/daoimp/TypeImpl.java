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

import com.waimai.dao.TypeDao;
import com.waimai.model.Type;
import com.waimai.util.DBHelper;

public class TypeImpl implements TypeDao{
	private QueryRunner runner;
	private Connection connection;
	private String sql;
	private PreparedStatement preparedStatement;
	public TypeImpl(){
		// TODO Auto-generated constructor stub
		runner = new QueryRunner();
		connection = DBHelper.getConnection();
	}
	
	
	public Type findById(int tid) {
		// TODO Auto-generated method stub
		sql = "select * from types where tid = ?";
		ResultSetHandler<Type> rsHandler = new BeanHandler<Type>(Type.class);
		Type type  = null;
		try {
			type = runner.query(connection,sql, rsHandler,tid);
		} catch (Exception e) {
			// TODO: handle exception	
			e.printStackTrace();
		}
		return type;
	}

	
	public boolean addType(Type type) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement stmt = null;
		if(type!=null){
			String sql= "insert into types(sid,name) "
					+ "values(?,?)";
			try{
				conn = DBHelper.getConnection();
				stmt = conn.prepareStatement(sql);
				stmt.execute("set Names utf8");
				stmt.setInt(1, type.getSid());
				stmt.setString(2, type.getName());
				
				stmt.executeUpdate();
				return true;
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		return false;
	}

	
	public boolean delType(int tid) {
		// TODO Auto-generated method stub
		sql="delete from `types` where `tid` = ? ";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, tid);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	
	public boolean modType(Type type) {
		// TODO Auto-generated method stub
		sql="update types set name=? where tid=?";
		try {
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(2, type.getTid());
			preparedStatement.setString(1, type.getName());
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

	
	public List<Type> getTypes(int sid) {
		// TODO Auto-generated method stub
		String sql = "select * from types where sid=? order by tid";
		ResultSetHandler<List<Type>> rSetHandler = new BeanListHandler<Type>(Type.class);
		List<Type> types = null;
		try {
			types = runner.query(connection,sql, rSetHandler, sid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return types;
	}
}
