package com.app.DAO;

import java.util.List;

import com.app.pojo.Inventry;

public interface InventryDAO {
	public void create(Inventry inventry);
	public void update(Inventry inventry);
	public void delet(Inventry inventry);
	public Inventry find(int id);
	public List<Inventry> getall();

}
