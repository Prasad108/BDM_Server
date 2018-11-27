package com.app.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.DAO.TypeDAO;
import com.app.Service.TypeService;
import com.app.pojo.Type;

@Service("TypeService")
public class TypeServiceImpl implements TypeService {
	
	@Autowired
	TypeDAO typeDAO;

	@Override
	public void create(Type type) {
		typeDAO.create(type);
	}

	@Override
	public void update(Type type) {
		typeDAO.update(type);
	}

	@Override
	public Type edit(int id) {
		return typeDAO.edit(id);
	}

	@Override
	public void delet(Type type) {
		typeDAO.delet(type);
	}

	@Override
	public Type find(int id) {
		return typeDAO.find(id);
	}

	@Override
	public List<Type> getall() {
		return typeDAO.getall();
	}

}
