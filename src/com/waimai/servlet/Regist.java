package com.waimai.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.waimai.daoimp.UserImpl;
import com.waimai.model.User;

public class Regist extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Regist() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json;charset=utf-8");//ָ�����صĸ�ʽΪJSON��ʽ  
		response.setCharacterEncoding("UTF-8");//setContentType��setCharacterEncoding��˳���ܵ������������޷�����������������  
		
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		
		String result = "0";
		
		UserImpl userimp = new UserImpl();
		//�жϴ��û��Ƿ����
		boolean flag = userimp.isExist(userName);
		if(flag) {
			result = "2";
		}
		else {
			User user = new User();
			user.setUserid(1);
			user.setUsername(userName);
			user.setPassword(passWord);
			user.setHeadimgurl(" ");
			user.setLocation("���ϿƼ���ѧ");
			user.setMoney(10000);
			user.setNickname(" ");
			user.setPhone(" ");
			user.setSex(1);
			boolean flag1 = userimp.addUser(user);
			if(flag1) {
				System.out.println("����û��ɹ�");
				result = "1";
			}
			else result = "0";
		}
		
		
		
		PrintWriter out =null;  
		out =response.getWriter();  
		out.write(result);  
		out.close();  
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
