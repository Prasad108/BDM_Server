package com.app.DAO;

import java.util.List;

import com.app.pojo.Type;

public interface TypeDAO {
	public void create(Type type);
	public void update(Type type);
	public Type edit(int id);
	public void delet(Type type);
	public Type find(int id);
	public List<Type> getall();

}
