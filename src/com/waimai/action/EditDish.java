package com.waimai.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.waimai.daoimp.DishImpl;
import com.waimai.model.Dish;

public class EditDish extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public EditDish() {
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
		String typeid=request.getParameter("typeid");
		String dishid=request.getParameter("dishid");
		String name=request.getParameter("name");
		//name = new String(name.getBytes("iso-8859-1"),"utf-8");
		String price=request.getParameter("price");
		String picture1=request.getParameter("picture1");
		//picture1 = new String(picture1.getBytes("iso-8859-1"),"utf-8");
		String picture2=request.getParameter("picture2");
		//picture2 = new String(picture2.getBytes("iso-8859-1"),"utf-8");
		
		
		Dish dish=new Dish();
		DishImpl dishImpl=new DishImpl();
		
		dish.setDid(Integer.valueOf(dishid));
		dish.setPrice(Double.valueOf(price));
		dish.setName(name);
		   if(picture1 == null ||"".equals(picture1)){
			   dish.setImg(picture2);
		   }
		   else
		   {
			   dish.setImg(picture1);
		   }
		
		if(!dishImpl.modDish(dish)){
			System.out.println("修改dish did=" + dishid + "失败");
		}
		else System.out.println("修改dish did=" + dishid + "成功");
		response.sendRedirect("SearchDish?typeid="+typeid);
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
