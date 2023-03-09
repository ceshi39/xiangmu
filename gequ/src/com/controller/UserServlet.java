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
	//重写处理请求的方法 service
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置请求的字符集
		request.setCharacterEncoding("utf-8");
		//设置响应字符集
		response.setCharacterEncoding("utf-8");
		response.setContentType("charset=utf-8");
		//获取输出流对象，他能写出数据
		PrintWriter pw = response.getWriter();
		//获取请求的caozuo
		String cz = request.getParameter("caozuo");
		if("denglu".equals(cz)){ //如果是登录
			String name = request.getParameter("name"); //用户名
			String pass = request.getParameter("pass"); //密码
			//调用UserDao验证登录的方法
			User uu = new UserDao().yanzheng(name, pass);
			JSONObject js = new JSONObject(); //json对象
			if( uu != null){ //有此用户
				js.put("msg", "成功");
				//把用户信息存到会话中
				HttpSession session = request.getSession();
				session.setAttribute("user", uu);
			}else{
				js.put("msg", "失败");
			}
			
			pw.print(js);//输出json对象
			pw.flush(); //清空输出对象
			pw.close(); //关闭输出对象
		}
	}
}
