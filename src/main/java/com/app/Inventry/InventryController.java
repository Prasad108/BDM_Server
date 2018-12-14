package com.app.Inventry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public Inventry addInventry(@RequestBody Inventry inventry) {
		return inventryService.create(inventry);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Inventry updateInventry(@PathVariable Integer id, @RequestBody Inventry inventry) {
		return inventryService.update(inventry);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public void deletInventry(@PathVariable Integer id) {
		inventryService.delet(id);
	}

}
