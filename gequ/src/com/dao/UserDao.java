package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.User;
import com.util.DBUtil;

//用户的dao, 数据库操作的代码
public class UserDao {
	
	//根据sql语句查询用户集合
	public ArrayList<User> cha(String sql){
		ArrayList<User> list = new ArrayList<User>();
		Connection conn = null; //连接对象
		PreparedStatement pre = null; //执行sql对象
		ResultSet set = null ; //结果集对象
		
		conn = DBUtil.getConn();//获取连接
		try {
			pre = conn.prepareStatement(sql);//获取执行sql对象
			set = pre.executeQuery();//sql执行查询获取结果集
			//将结果集的数据取出放到集合中
			while (set.next()) {
				//创建一个User对象
				User tz = new User();
				tz.setId( set.getInt("id") ); //获取编号
				tz.setName( set.getString("name")); //用户名
				tz.setPass( set.getString("pass")); //密码
				list.add(tz);//将帖子对象放到集合中
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//关闭资源
			DBUtil.close(set, pre, conn);
		}
		return list; //返回集合
	}
	
	//验证用户名和密码
	public User yanzheng(String name, String pass){
		String sql = "select * from user where name='"+name+"' and pass='"+pass+"'  ";
		//调用查询的方法
		ArrayList<User> list = cha(sql);
		
		if(list.size()>0){
			return list.get(0); //返回集合中的第一个数据
		}else{
			return null; //
		}
	}
	
	
	
	
	
	
}
