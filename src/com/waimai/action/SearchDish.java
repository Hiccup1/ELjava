package com.waimai.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.waimai.common.Data;
import com.waimai.daoimp.DishImpl;
import com.waimai.daoimp.TypeImpl;

public class SearchDish extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SearchDish() {
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
		
			DishImpl dishimp = new DishImpl();
			
			String typeid=request.getParameter("typeid");
			int tid = Integer.parseInt(typeid);
			
			System.out.println("≤È—Ødish tid =" + tid);
			
			List list=dishimp.findByType(tid);		
			request.getSession().setAttribute(Data.DISHES, list);
			request.getSession().setAttribute(Data.CURRENT_TYPE, typeid);
			response.sendRedirect("../food.jsp");
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
