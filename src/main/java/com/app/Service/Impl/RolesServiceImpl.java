package com.app.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.DAO.LanguagesDAO;
import com.app.DAO.RolesDAO;
import com.app.Service.RolesService;
import com.app.pojo.Roles;


@Service("rolesService")
public class RolesServiceImpl implements RolesService {

	@Autowired
	RolesDAO rolesDAO;
	
	@Override
	public void create(Roles role) {
		rolesDAO.create(role);
	}

	@Override
	public void update(Roles role) {
		rolesDAO.update(role);
	}

	@Override
	public Roles edit(int id) {
		return rolesDAO.edit(id);
	}

	@Override
	public void delet(Roles role) {
		rolesDAO.delet(role);
	}

	@Override
	public Roles find(int id) {
		return rolesDAO.find(id);
	}

	@Override
	public List<Roles> getall() {
		return rolesDAO.getall();
	}

}
