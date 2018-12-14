package com.app.Type;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public Type addType(@RequestBody Type type) {
		return typeService.create(type);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Type updateType(@PathVariable Integer id,@RequestBody Type type) {
		return typeService.update(type);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = "application/json")
	public void deletType(@PathVariable Integer id) {
		typeService.delet(id);
	}

}
