package com.app.Languages;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/languages") 
public class LanguagesController {
	
	@Autowired
	LanguagesService languagesService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Languages> getAllLanguages() {
		System.out.println("***************** getAllLanguages *****************");
	return languagesService.getall();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Languages getLanguages(@PathVariable Integer id) {
		return languagesService.find(id);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public void addLanguages(@RequestBody Languages languages) {
		languagesService.create(languages);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updateLanguages(@PathVariable Integer id,@RequestBody Languages languages) {
		languagesService.update(languages);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = "application/json")
	public void deletBook(@PathVariable Integer id) {
		languagesService.delet(id);
	}

}
