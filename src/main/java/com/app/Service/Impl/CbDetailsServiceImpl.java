package com.app.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.DAO.CbDetailsDAO;
import com.app.Service.CbDetailsService;
import com.app.pojo.CbDetails;

@Service("cbDetailsService")
public class CbDetailsServiceImpl implements CbDetailsService {

	@Autowired
	CbDetailsDAO cbDetailsDAO;
	
	@Override
	public void create(CbDetails cbDetails) {
		cbDetailsDAO.create(cbDetails);

	}

	@Override
	public void update(CbDetails cbDetails) {
		cbDetailsDAO.update(cbDetails);

	}

	@Override
	public void delet(CbDetails cbDetails) {
		cbDetailsDAO.delet(cbDetails);

	}

	@Override
	public CbDetails find(int id) {
		return cbDetailsDAO.find(id);
	}

	@Override
	public List<CbDetails> getall() {
		return cbDetailsDAO.getall();
	}

}
