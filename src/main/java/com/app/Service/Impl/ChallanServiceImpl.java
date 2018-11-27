package com.app.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.DAO.ChallanDAO;
import com.app.Service.ChallanService;
import com.app.pojo.Challan;

@Service("challanService")
public class ChallanServiceImpl implements ChallanService {
	
	@Autowired
	ChallanDAO challanDAO ; 

	@Override
	public void create(Challan challan) {
		challanDAO.create(challan);

	}

	@Override
	public void update(Challan challan) {
		challanDAO.update(challan);

	}

	@Override
	public void delet(Challan challan) {
		challanDAO.delet(challan);

	}

	@Override
	public Challan find(int id) {
		return challanDAO.find(id);
	}

	@Override
	public List<Challan> getall() {
		return challanDAO.getall();
	}

}
