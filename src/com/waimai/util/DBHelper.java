package com.waimai.util;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBHelper {
	private static String driver="com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://localhost:3306/waimai?useUnicode=true&characterEncoding=utf-8";
	private static String username="root";
	private static String password="123456";
	/*
	 * �����ݿ�����
	 */
	public static Connection getConnection() {
		try {
			Class.forName(driver);//ע��mysql����
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
