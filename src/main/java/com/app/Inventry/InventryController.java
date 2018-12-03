package com.app.Inventry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public Inventry addInventry(@RequestBody Inventry inventry) {
		return inventryService.create(inventry);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Inventry updateInventry(@PathVariable Integer id,@RequestBody Inventry inventry) {
		return inventryService.update(inventry);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deletInventry(@PathVariable Integer id) {
		inventryService.delet(id);
	}

}
