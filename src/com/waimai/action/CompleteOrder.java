package com.waimai.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.waimai.common.Data;
import com.waimai.daoimp.OrderImpl;
import com.waimai.model.Order;

public class CompleteOrder extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CompleteOrder() {
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

		String state = request.getParameter("state");
		String orderid=request.getParameter("orderid");
		String sid=request.getParameter("sid");
		Order order=new Order();
		order.setSid(Integer.valueOf(Data.DONE));
		order.setOid(Integer.valueOf(orderid));
		OrderImpl orderImpl=new OrderImpl();
		if(orderImpl.modOrder(order))
			System.out.println("已接单");
		else 
			System.out.println("接单失败");
		if(state.equals(Data.DOING))
			response.sendRedirect("ShowDoingOrder?sid=" + sid);
		else if(state.equals(Data.DONE))
			response.sendRedirect("ShowDoneOrder?sid=" + sid);
		else 
			response.sendRedirect("../index.jsp");
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
