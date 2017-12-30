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
import com.waimai.daoimp.ShopImpl;
import com.waimai.daoimp.TypeImpl;
import com.waimai.daoimp.UserImpl;
import com.waimai.model.Shop;
import com.waimai.model.Type;
import com.waimai.model.User;

/**
 * Servlet implementation class UserRegist
 */
@WebServlet("/UserRegist")
public class UserRegist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegist() {
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
		String shopname=request.getParameter("shopname");
		shopname = new String(shopname.getBytes("iso-8859-1"),"utf-8");
		System.out.println("����ע��:" + username +" " + password + " " + shopname);
		//�ж��û���ȷ��
		String result = "0";
		
		UserImpl userimp = new UserImpl();
		//�жϴ��û��Ƿ����
		boolean flag = userimp.isExist(username);
		if(flag) {
			//���û��Ѵ���
			result = "2";
			response.sendRedirect("../regist.jsp?result=" + result);
		}
		else {
			User user = new User();
			user.setUserid(0);
			user.setUsername(username);
			user.setPassword(password);
			user.setHeadimgurl(" ");
			user.setLocation("���ϿƼ���ѧ");
			user.setMoney(0);
			user.setNickname(" ");
			user.setPhone(" ");
			user.setSex(1);
			boolean flag1 = userimp.addUser(user);
			if(flag1) {
				System.out.println("����û��ɹ�" + username);
				user = userimp.findByUserName(username);
				int uid = user.getUid();
				
				ShopImpl shopimp = new ShopImpl();
				Shop shop = new Shop();
				shop.setImg(" ");
				shop.setLocation(" ");
				shop.setName(shopname);
				shop.setPhone(" ");
				shop.setRemark(" ");
				shop.setUid(uid);
				boolean flag2 = shopimp.addShop(shop);
				if(flag2) {
					result = "11";
					System.out.println("��ӵ��̳ɹ�" + username + " " + shopname);
					shop = shopimp.findByUid(uid);
					
					int sid = shop.getSid();
					List<Type> list = new ArrayList<Type>();
					TypeImpl typeimp = new TypeImpl();
					list = typeimp.getTypes(sid);
					
					request.getSession().setAttribute(Data.TYPES, list);
					request.getSession().setAttribute(Data.SHOPS, shop);
					
					response.sendRedirect("../design.jsp");
				} else {
					result = "10";
					response.sendRedirect("../regist.jsp?result=" + result);
				}
				
			}
			else {
				result = "00";
				response.sendRedirect("../regist.jsp?result=" + result);
			}
		}
		
	}

}
