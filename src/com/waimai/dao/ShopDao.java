package com.waimai.dao;

import java.util.List;

import com.waimai.model.Shop;

public interface ShopDao {
	public Shop findBySid(int sid);
	public Shop findByUid(int uid);
	public List<Shop> showAllShops();
	public boolean addShop(Shop shop);
	public boolean modifyShop(Shop shop);
}
