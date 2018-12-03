package com.app.Type;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/type") 
public class TypeController {
	
	@Autowired
	TypeService typeService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Type> getAllTypes() {
	return typeService.getall();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Type getType(@PathVariable Integer id) {
		return typeService.find(id);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public void addType(@RequestBody Type type) {
		typeService.create(type);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updateType(@PathVariable Integer id,@RequestBody Type type) {
		typeService.update(type);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deletType(@PathVariable Integer id) {
		typeService.delet(id);
	}

}
