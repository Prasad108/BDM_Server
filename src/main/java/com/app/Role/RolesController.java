package com.app.Role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public void addRoles(@RequestBody Roles roles) {
		rolesService.create(roles);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updateRoles(@PathVariable Integer id,@RequestBody Roles roles) {
		rolesService.update(roles);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = "application/json")
	public void deletRoles(@PathVariable Integer id) {
		rolesService.delet(id);
	}

}
