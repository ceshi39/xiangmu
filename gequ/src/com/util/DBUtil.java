package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//���ݿ⹤���� �ṩ��ȡ���ӣ��ر���Դ�͹�������ɾ�ķ���
public class DBUtil {
	private static final String URL = "jdbc:mysql://localhost:3306/music_db";//���ӵ�ַ
	private static final String NAME = "root";//�û���
	private static final String PASS = "root";//����
	//��������
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	//��ȡ����
	public static Connection getConn(){
		try {
			return DriverManager.getConnection(URL, NAME, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//�ر���Դ ��������� ִ��sql�������Ӷ���
	public static void close(ResultSet set, PreparedStatement pre, Connection conn){
		try {
			if(set!=null) set.close();
			if(pre!=null) pre.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//��������ɾ�ķ���
	public static void gai(String sql, List parm){
		//��ȡ����
		Connection conn = getConn();
		//��ȡִ��sql����
		try {
			PreparedStatement pre = conn.prepareStatement(sql);
			//���ò���
			if (parm != null && parm.size() > 0) {
				for (int i = 0; i < parm.size(); i++) {
					pre.setObject( i+1, parm.get(i) );
				}
			}
			//ִ��sql
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
