package com.waimai.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.waimai.common.Data;
import com.waimai.daoimp.TypeImpl;
import com.waimai.model.Type;

/**
 * Servlet implementation class SearchType
 */
@WebServlet("/SearchType")
public class SearchType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchType() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
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
		String sidstring = request.getParameter("sid");
		int sid = Integer.parseInt(sidstring);
		System.out.println("≤È—Øsid=" + sid + " types");

		List<Type> list = new ArrayList<Type>();
		TypeImpl typeimp = new TypeImpl();
		list = typeimp.getTypes(sid);
		
		request.getSession().setAttribute(Data.TYPES, list);
		response.sendRedirect("../edittype.jsp");
	
	}

}
