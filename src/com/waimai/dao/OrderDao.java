package com.waimai.dao;

import java.util.List;

import com.waimai.model.Order;

public interface OrderDao {
	public Order findById(int oid);
	public boolean addOrder(Order order);
	public boolean delOrder(int oid);
	public boolean modOrder(Order order);
	public boolean redelOrder(int oid);
	public List<Order> ShowDoingOrders(int sid);
	public List<Order> ShowDoneOrders(int sid);
}
