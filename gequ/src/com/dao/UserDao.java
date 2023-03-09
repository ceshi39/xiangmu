package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.User;
import com.util.DBUtil;

//�û���dao, ���ݿ�����Ĵ���
public class UserDao {
	
	//����sql����ѯ�û�����
	public ArrayList<User> cha(String sql){
		ArrayList<User> list = new ArrayList<User>();
		Connection conn = null; //���Ӷ���
		PreparedStatement pre = null; //ִ��sql����
		ResultSet set = null ; //���������
		
		conn = DBUtil.getConn();//��ȡ����
		try {
			pre = conn.prepareStatement(sql);//��ȡִ��sql����
			set = pre.executeQuery();//sqlִ�в�ѯ��ȡ�����
			//�������������ȡ���ŵ�������
			while (set.next()) {
				//����һ��User����
				User tz = new User();
				tz.setId( set.getInt("id") ); //��ȡ���
				tz.setName( set.getString("name")); //�û���
				tz.setPass( set.getString("pass")); //����
				list.add(tz);//�����Ӷ���ŵ�������
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//�ر���Դ
			DBUtil.close(set, pre, conn);
		}
		return list; //���ؼ���
	}
	
	//��֤�û���������
	public User yanzheng(String name, String pass){
		String sql = "select * from user where name='"+name+"' and pass='"+pass+"'  ";
		//���ò�ѯ�ķ���
		ArrayList<User> list = cha(sql);
		
		if(list.size()>0){
			return list.get(0); //���ؼ����еĵ�һ������
		}else{
			return null; //
		}
	}
	
	
	
	
	
	
}
