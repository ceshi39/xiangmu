package com.controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.bean.User;
import com.dao.UserDao;
//ceshifadfas
public class UserServlet extends HttpServlet {
	//��д��������ķ��� service
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//����������ַ���
		request.setCharacterEncoding("utf-8");
		//������Ӧ�ַ���
		response.setCharacterEncoding("utf-8");
		response.setContentType("charset=utf-8");
		//��ȡ�������������д������
		PrintWriter pw = response.getWriter();
		//��ȡ�����caozuo
		String cz = request.getParameter("caozuo");
		if("denglu".equals(cz)){ //����ǵ�¼
			String name = request.getParameter("name"); //�û���
			String pass = request.getParameter("pass"); //����
			//����UserDao��֤��¼�ķ���
			User uu = new UserDao().yanzheng(name, pass);
			JSONObject js = new JSONObject(); //json����
			if( uu != null){ //�д��û�
				js.put("msg", "�ɹ�");
				//���û���Ϣ�浽�Ự��
				HttpSession session = request.getSession();
				session.setAttribute("user", uu);
			}else{
				js.put("msg", "ʧ��");
			}
			
			pw.print(js);//���json����
			pw.flush(); //����������
			pw.close(); //�ر��������
		}
	}
}
