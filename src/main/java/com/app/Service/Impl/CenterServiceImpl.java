package com.app.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.DAO.CenterDAO;
import com.app.Service.CenterService;
import com.app.pojo.Center;

@Service("centerService")
public class CenterServiceImpl implements CenterService {
	@Autowired
	CenterDAO centerDAO;

	@Override
	public void create(Center center) {
		centerDAO.create(center);
	}

	@Override
	public void update(Center center) {
		centerDAO.update(center);
	}

	@Override
	public Center edit(int id) {
		return centerDAO.edit(id);
	}

	@Override
	public void delet(Center center) {
		centerDAO.delet(center);
	}

	@Override
	public Center find(int id) {
		return centerDAO.find(id);
	}

	@Override
	public List<Center> getall() {
		return centerDAO.getall();
	}

}
