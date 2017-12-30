package com.waimai.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.waimai.daoimp.DishImpl;
import com.waimai.model.Dish;

public class AddDish extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddDish() {
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

		this.doPost(request, response);
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
	
		request.setCharacterEncoding("utf-8");
		String pname=request.getParameter("name");
		//pname = new String(pname.getBytes("iso-8859-1"),"utf-8");
		String ptype=request.getParameter("price");
		Double price = Double.parseDouble(ptype);
		String pinfo=request.getParameter("picture");
		String tidid=request.getParameter("tid");
		int tid = Integer.parseInt(tidid);
		String sidid = request.getParameter("sid");
		int sid = Integer.parseInt(sidid);
		
		System.out.println("添加dish tid=" + tid);
		
		
		DishImpl dishimp = new DishImpl();
		Dish dish = new Dish();
		dish.setImg(pinfo);
		dish.setName(pname);
		dish.setPrice(price);
		dish.setRemark("1");
		dish.setSellcount(0);
		dish.setSid(sid);
		dish.setTid(tid);
		
		boolean flag = dishimp.addDish(dish);
		
		if(flag){
			System.out.println("添加dish tid=" + tid + "成功");
			response.sendRedirect("SearchDish?typeid="+tid);
		}else{
			System.out.println("添加dish tid=" + tid + "失败");
			response.sendRedirect("SearchDish?typeid="+tid);
		}
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
