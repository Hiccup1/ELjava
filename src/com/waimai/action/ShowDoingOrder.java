package com.waimai.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.waimai.common.Data;
import com.waimai.daoimp.OrderImpl;
import com.waimai.model.Order;

public class ShowDoingOrder extends HttpServlet {
	
	public void init() throws ServletException{
		super.init();
	}
	
	public void destroy(){
		super.destroy();
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		this.doPost(request,response);
		
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String sidstr = request.getParameter("sid");
		int sid = Integer.parseInt(sidstr);
		List<Order> list = null;
		OrderImpl orderimp = new OrderImpl();
		list = orderimp.ShowDoingOrders(sid);
		if (list==null)
			System.out.println("orders null");
		else 
			System.out.println("未完成订单数量?" + list.size());
		request.getSession().setAttribute(Data.ORDERS, list);	
		response.sendRedirect("../weiwancheng.jsp");
	}
	
	public ShowDoingOrder(){
		super();
	}

}
