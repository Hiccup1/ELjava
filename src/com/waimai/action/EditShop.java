package com.waimai.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.waimai.common.Data;
import com.waimai.daoimp.ShopImpl;
import com.waimai.model.Shop;

/**
 * Servlet implementation class EditShop
 */
@WebServlet("/EditShop")
public class EditShop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditShop() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String phonenumber=request.getParameter("phonenumber");
		String address=request.getParameter("address");
		String remark=request.getParameter("remark");
		String shopname=request.getParameter("shopname");
		String shopimg=request.getParameter("shopimg");
		String sidstring = request.getParameter("sid");
		int sid = Integer.parseInt(sidstring);
		
		System.out.println("修改shop sid=" + sid);
		
		Shop shop=new Shop();
		ShopImpl shopImpl=new ShopImpl();
		shop.setSid(sid);
		shop.setLocation(address);
		shop.setName(shopname);
		shop.setPhone(phonenumber);
		shop.setRemark(remark); 
		shop.setImg(shopimg);
		if(!shopImpl.modifyShop(shop)){
			System.out.println("修改shop name: " + shopname+ "  sid: "+ sid + "失败");
		}
		else System.out.println("修改shop name: " + shopname+ "  sid: "+ sid + "成功");
		/* change */
		shop=shopImpl.findBySid(sid);
		request.getSession().setAttribute(Data.SHOPS, shop);
		response.sendRedirect("../design.jsp");
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
