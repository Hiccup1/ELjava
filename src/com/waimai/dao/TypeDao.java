package com.waimai.dao;

import java.util.List;

import com.waimai.model.Type;

public interface TypeDao {
	public Type findById(int tid);
	public List<Type> getTypes(int sid);
	public boolean addType(Type type);
	public boolean delType(int tid);
	public boolean modType(Type type);
}
