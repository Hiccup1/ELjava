package com.waimai.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.waimai.daoimp.OrderImpl;
import com.waimai.daoimp.UserImpl;
import com.waimai.model.Order;
import com.waimai.model.User;

import net.sf.json.JSONArray;

public class ShowOrder extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowOrder() {
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
		response.setContentType("application/json;charset=utf-8");//指定返回的格式为JSON格式  
		response.setCharacterEncoding("UTF-8");//setContentType与setCharacterEncoding的顺序不能调换，否则还是无法解决中文乱码的问题  
		
		String userName = request.getParameter("userName");
		System.out.println("查询order username=" + userName);
		
		List<Order> list = new ArrayList<Order>();
		OrderImpl orderimp = new OrderImpl();
		
		UserImpl userimp = new UserImpl();
		User user = new User();
		user = userimp.findByUserName(userName);
		list = orderimp.findOrdersByUid(user.getUid());
		//创建json集合
        JSONArray jsonArray = JSONArray.fromObject(list);
		String jsonStr = jsonArray.toString();
		PrintWriter out =null;  
		out =response.getWriter();  
		out.write(jsonStr);  
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
