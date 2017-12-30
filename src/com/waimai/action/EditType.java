package com.waimai.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.waimai.model.Type;
import com.waimai.daoimp.TypeImpl;

/**
 * Servlet implementation class EditType
 */
@WebServlet("/EditType")
public class EditType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditType() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		doPost(req, resp);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
		String typeid=req.getParameter("typeid");
		String typename=req.getParameter("typename");
		typename = new String(typename.getBytes("iso-8859-1"),"utf-8");
		String sidstring = req.getParameter("sid");
		int sid = Integer.parseInt(sidstring);
		System.out.println("准备修改typename" + typename + "tid" + typeid);
		Type type=new Type();
		TypeImpl typeImpl=new TypeImpl();
		type.setName(typename);
		type.setTid(Integer.valueOf(typeid));
		if(!typeImpl.modType(type))
			System.out.println("修改typename失败");
		resp.sendRedirect("SearchType?sid=" + sid);
	}

}
