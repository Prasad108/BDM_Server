package com.app.Center;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/center") 
public class CenterController {
	
	@Autowired
	CenterService centerService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Center> getAllCenter() {
		return centerService.getall();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Center getCenter(@PathVariable Integer id) {
		return centerService.find(id);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public Center addCenter(@RequestBody Center center) {
		return centerService.create(center);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Center updateCenter(@PathVariable Integer id,@RequestBody Center center) {
		return centerService.update(center);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = "application/json")
	public void updateCenter(@PathVariable Integer id) {
		centerService.delet(id);
	}

}
