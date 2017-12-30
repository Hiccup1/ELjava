package com.waimai.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.waimai.daoimp.TypeImpl;
import com.waimai.model.Type;


/**
 * Servlet implementation class AddType
 */
@WebServlet("/AddType")
public class AddType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddType() {
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
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String typename=request.getParameter("typename");
		//typename = new String(typename.getBytes("iso-8859-1"),"utf-8");
		String sidString=request.getParameter("sid");
		int sid = Integer.parseInt(sidString);
		
		System.out.println("添加type ：typename " + typename + " sid=" + sid);
		
		TypeImpl typeimp = new TypeImpl();
		Type type = new Type();
		type.setName(typename);
		type.setSid(sid);
		
		boolean flag = typeimp.addType(type);
		
		if(flag){
			System.out.println("添加type成功 ：typename " + typename + " sid=" + sid);
			response.sendRedirect("SearchType?sid=" + sid);
		}else{
			System.out.println("添加type失败 ：typename " + typename + " sid=" + sid);
			response.sendRedirect("SearchType?sid=" + sid);
		}
		
	}

}
