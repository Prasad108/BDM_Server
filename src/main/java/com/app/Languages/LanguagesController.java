package com.app.Languages;

import java.security.Principal;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public Languages addLanguages(@RequestBody Languages languages) {
		return languagesService.create(languages);
	}
	
	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updateLanguages(@PathVariable Integer id,@RequestBody Languages languages) {
		languagesService.update(languages);
	}
	
	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = "application/json")
	public void deletBook(@PathVariable Integer id) {
		languagesService.delet(id);
	}
	
	@RequestMapping(value = "/getAllLanguagesForBookNameInUsersInventory/{id}", method = RequestMethod.GET)
	public List<Languages> getAllLanguagesForBookNameInUsersInventory(@PathVariable Integer id,Principal principal) {
		return languagesService.getAllLanguagesForBookNameInUsersInventory(id, principal.getName());
	}
	
	
	@RequestMapping(value = "/getLanguagesOfAllBooksHavingBookName/{id}", method = RequestMethod.GET)
	public List<Languages> getLanguagesOfAllBooksHavingBookName(@PathVariable Integer id) {
		return languagesService.getLanguagesOfAllBooksHavingBookName(id);
	}
	
	

}
