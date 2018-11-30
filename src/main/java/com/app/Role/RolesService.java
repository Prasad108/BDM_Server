package com.app.Role;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RolesService  {

	@Autowired
	RolesRepository rolesRepository;
	
	public void create(Roles roles) {
		rolesRepository.save(roles);
	}

	public void update(Roles roles) {
		rolesRepository.save(roles);
	}

	public void delet(Integer id) {
		rolesRepository.deleteById(id);
	}

	public Roles find(int id) {
		return rolesRepository.findById(id).get();
	}

	public List<Roles> getall() {
		List<Roles> list = new ArrayList<>();
		rolesRepository.findAll().forEach(list::add);
		return list;
	}

}
