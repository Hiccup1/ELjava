package com.waimai.dao;

import com.waimai.model.OrderItem;

public interface OrderItemDao {
	public OrderItem findById(int oid);
	public boolean addOrderItem(OrderItem item);
	public boolean delOrderItem(OrderItem item);
	public boolean modOrderItem(OrderItem item);
}
