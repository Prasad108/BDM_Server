package com.app.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.DAO.InventryDAO;
import com.app.Service.InventryService;
import com.app.pojo.Inventry;

@Service("inventryService")
public class InventryServiceImpl implements InventryService {
	
	@Autowired
	InventryDAO inventryDAO;

	@Override
	public void create(Inventry inventry) {
		inventryDAO.create(inventry);

	}

	@Override
	public void update(Inventry inventry) {
		inventryDAO.update(inventry);

	}

	@Override
	public void delet(Inventry inventry) {
		inventryDAO.delet(inventry);

	}

	@Override
	public Inventry find(int id) {
		return inventryDAO.find(id);
	}

	@Override
	public List<Inventry> getall() {
		return inventryDAO.getall();
	}

}
