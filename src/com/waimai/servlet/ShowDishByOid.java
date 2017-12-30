package com.waimai.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.waimai.daoimp.DishImpl;
import com.waimai.daoimp.OrderItemImpl;
import com.waimai.model.Dish;
import com.waimai.model.OrderItem;

import net.sf.json.JSONArray;

public class ShowDishByOid extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowDishByOid() {
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

		String oid1 = request.getParameter("oid");
		int oid = Integer.parseInt(oid1);
		System.out.println("获取dishs传入的oid：" + oid);
		
		
		DishImpl dishimpl = new DishImpl();
		OrderItemImpl orderitemimp = new OrderItemImpl();
		List<OrderItem> olist = orderitemimp.findByOrderId(oid);
		List<Dish> list = new ArrayList<Dish>();
		int len = olist.size();
		for(int i = 0; i < len; i++) {
			int did = olist.get(i).getDid();
			Dish dish = dishimpl.findById(did);
			dish.setSellcount(olist.get(i).getNum());
			list.add(dish);
		}
		
		
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
