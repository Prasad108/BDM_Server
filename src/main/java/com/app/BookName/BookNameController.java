package com.app.BookName;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookName") 
public class BookNameController {
	
	@Autowired
	BookNameService bookNameService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<BookName> getAllBookNames() {
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

}