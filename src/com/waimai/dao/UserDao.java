package com.waimai.dao;

import com.waimai.model.User;

public interface UserDao {
	public User findByUserName(String userName);
	public boolean addUser(User user);
	public boolean isExist(String userName);
}
