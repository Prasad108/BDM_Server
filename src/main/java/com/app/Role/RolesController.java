package com.app.Role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
@RequestMapping("/roles") 
public class RolesController {
	
	@Autowired
	RolesService rolesService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Roles> getAllRoless() {
	return rolesService.getall();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Roles getRoles(@PathVariable Integer id) {
		return rolesService.find(id);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public void addRoles(@RequestBody Roles roles) {
		rolesService.create(roles);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updateRoles(@PathVariable Integer id,@RequestBody Roles roles) {
		rolesService.update(roles);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deletRoles(@PathVariable Integer id) {
		rolesService.delet(id);
	}

}
