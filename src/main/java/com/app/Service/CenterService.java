package com.app.Service;

import java.util.List;

import com.app.pojo.Center;

public interface CenterService {

	public void create(Center center);
	public void update(Center center);
	public Center edit(int id);
	public void delet(Center center);
	public Center find(int id);
	public List<Center> getall();
}
