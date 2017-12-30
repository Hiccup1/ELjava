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
import com.waimai.model.Shop;
import com.waimai.model.Type;
import com.waimai.model.User;
import com.waimai.daoimp.ShopImpl;
import com.waimai.daoimp.TypeImpl;
import com.waimai.daoimp.UserImpl;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		System.out.println("店铺登录:" + username +" " + password);
		//判断用户正确性
		String result = "0";
		
		UserImpl userimp = new UserImpl();
		//判断此用户是否存在
		boolean flag = userimp.isExist(username);
		if(!flag) {
			result = "0"; //bu存在
			response.sendRedirect("../login.jsp?result=" + result);
		}
		else {
			//判断此用户密码是否正确
			User user = userimp.findByUserName(username);
			String passWord1 = user.getPassword();
			int userid = user.getUserid();
			if(userid == 1) {
				result = "3"; //商家用户和app用户不能通用
				response.sendRedirect("../login.jsp?result=" + result);
			}else if(passWord1.equals(password)) {
				
				int uid = user.getUid();
				Shop shop = new Shop();
				ShopImpl shopimp = new ShopImpl();
				shop = shopimp.findByUid(uid);
				
				int sid = shop.getSid();
				List<Type> list = new ArrayList<Type>();
				TypeImpl typeimp = new TypeImpl();
				list = typeimp.getTypes(sid);
				
				request.getSession().setAttribute(Data.TYPES, list);
				request.getSession().setAttribute(Data.SHOPS, shop);
				response.sendRedirect("../design.jsp");
			}
			else {
				result = "2"; //密码不正确
				response.sendRedirect("../login.jsp?result=" + result);
			}
		}
		
	}

}
