package com.app.BookName;

import java.security.Principal;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ArrayNode;

@RestController
@RequestMapping("/bookName") 
public class BookNameController {
	
	@Autowired
	BookNameService bookNameService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ArrayNode getAllBookNames() {
	return bookNameService.getall();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public BookName getBookName(@PathVariable Integer id) {
		return bookNameService.find(id);
	}
	
	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public BookName addBookName(@RequestBody BookName bookName) {
		return bookNameService.create(bookName);
	}
	
	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public BookName updateBookName(@PathVariable Integer id,@RequestBody BookName bookName) {
		return bookNameService.update(bookName);
	}
	
	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = "application/json")
	public void deletBookName(@PathVariable Integer id) {
		bookNameService.delet(id);
	}
	
	
	
	@RequestMapping(value = "/getAllBookNameOfUsersInventory", method = RequestMethod.GET,produces = "application/json")
	public List<BookName> getAllBookNameOfUsersInventory(Principal principal) {
		return bookNameService.getAllBookNameOfUsersInventory(principal.getName());
	}

}
