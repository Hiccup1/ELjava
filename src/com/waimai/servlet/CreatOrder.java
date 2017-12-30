package com.waimai.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.waimai.daoimp.DishImpl;
import com.waimai.daoimp.OrderImpl;
import com.waimai.daoimp.OrderItemImpl;
import com.waimai.daoimp.ShopImpl;
import com.waimai.daoimp.UserImpl;
import com.waimai.model.Dish;
import com.waimai.model.Order;
import com.waimai.model.OrderItem;
import com.waimai.model.Shop;
import com.waimai.model.User;
import com.waimai.util.JsonParse;

public class CreatOrder extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CreatOrder() {
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
		
		String orderitemstring = request.getParameter("orderitemstring");
		String userName = request.getParameter("userName");
		
		
		double price = 0.0;
		
		List<OrderItem> list = JsonParse.getOrderItem(orderitemstring);
		
		int len = list.size();
		OrderItemImpl orderitemimp = new OrderItemImpl();
		DishImpl dishimp = new DishImpl();
		for(int i = 0; i < len; i++) {
			boolean flag = orderitemimp.addOrderItem(list.get(i));
			Dish dish = dishimp.findById(list.get(i).getDid());
			price += list.get(i).getNum() * dish.getPrice();
			if(flag) {
				System.out.println("添加orderitem成功");
			}
			else System.out.println("添加orderitem失败");
		}
		
		UserImpl userimp = new UserImpl();
		User user = userimp.findByUserName(userName);
		ShopImpl shopimp = new ShopImpl();
		Shop shop = shopimp.findBySid(list.get(0).getSid());
		
		Order order =  new Order();
		order.setName(shop.getName());
		order.setDiscount(1);
		order.setLocation("湖南科技大学");
		order.setOid(list.get(0).getOid());
        order.setPay(1);
        order.setPhone("1");
        order.setPrice(price);
        order.setRemark(shop.getImg());
        order.setSex(1);
        order.setSid(list.get(0).getSid());
        order.setState(1);
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date=new java.util.Date();  
        String str=sdf.format(date); 
        System.out.println(str);
        order.setTime(str);
        order.setUid(user.getUid());
        order.setWlocation("湖南科技大学");
        
        OrderImpl orderimp = new OrderImpl();
        boolean flag = orderimp.addOrder(order);
        String result = "0";
        if(flag) {
        	System.out.println("添加order成功" + list.get(0).getOid());
        	result = "1";
        }
        else System.out.println("添加order失败" + list.get(0).getOid());
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
