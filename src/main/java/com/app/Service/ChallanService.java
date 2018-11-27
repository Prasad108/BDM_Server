package com.app.Service;

import java.util.List;

import com.app.pojo.Challan;

public interface ChallanService {
	public void create(Challan challan);
	public void update(Challan challan);
	public void delet(Challan challan);
	public Challan find(int id);
	public List<Challan> getall();

}
