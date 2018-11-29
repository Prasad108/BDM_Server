package com.app.Center;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public void addCenter(@RequestBody Center center) {
		centerService.create(center);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updateCenter(@PathVariable Integer id,@RequestBody Center center) {
		centerService.update(center);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void updateCenter(@PathVariable Integer id) {
		centerService.delet(id);
	}

}