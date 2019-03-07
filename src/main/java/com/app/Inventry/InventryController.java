package com.app.Inventry;

import java.security.Principal;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
@RequestMapping("/inventry")
public class InventryController {

	@Autowired
	InventryService inventryService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Inventry> getAllInventrys() {
		return inventryService.getall();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Inventry getInventry(@PathVariable Integer id) {
		return inventryService.find(id);
	}
	
	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public Inventry addInventry(@RequestBody Inventry inventry) {
		return inventryService.create(inventry);
	}

	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Inventry updateInventry(@PathVariable Integer id, @RequestBody Inventry inventry) {
		return inventryService.update(inventry);
	}
	
	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public void deletInventry(@PathVariable Integer id) {
		inventryService.delet(id);
	}

	@RequestMapping(value = "/getInventryDetailsOfCurrentUser", method = RequestMethod.GET)
	public String getInventryDetailsOfCurrentUser(Principal principal) {
		return inventryService.getInventryDetailsOfCurrentUser(principal.getName());
	}

}
