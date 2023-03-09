package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//数据库工具类 提供获取连接，关闭资源和公共的增删改方法
public class DBUtil {
	private static final String URL = "jdbc:mysql://localhost:3306/music_db";//连接地址
	private static final String NAME = "root";//用户名
	private static final String PASS = "root";//密码
	//加载驱动
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	//获取连接
	public static Connection getConn(){
		try {
			return DriverManager.getConnection(URL, NAME, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//关闭资源 结果集对象， 执行sql对象，连接对象
	public static void close(ResultSet set, PreparedStatement pre, Connection conn){
		try {
			if(set!=null) set.close();
			if(pre!=null) pre.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//公共的增删改方法
	public static void gai(String sql, List parm){
		//获取连接
		Connection conn = getConn();
		//获取执行sql对象
		try {
			PreparedStatement pre = conn.prepareStatement(sql);
			//设置参数
			if (parm != null && parm.size() > 0) {
				for (int i = 0; i < parm.size(); i++) {
					pre.setObject( i+1, parm.get(i) );
				}
			}
			//执行sql
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
