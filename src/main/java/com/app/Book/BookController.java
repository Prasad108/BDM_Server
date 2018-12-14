package com.app.Book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.Languages.LanguagesService;
import com.app.Type.TypeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/book") 
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@Autowired
	LanguagesService languagesService;
	
	@Autowired
	TypeService typeService;
	
	ObjectMapper mapper = new ObjectMapper();
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Book> getAllBooks() {
	return bookService.getall();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public Book getBook(@PathVariable Integer id) {
		return bookService.find(id);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/", method = RequestMethod.PUT,produces = "application/json")
	public Book addBook(@RequestBody Book book) {
		return bookService.create(book);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Book updateBook(@PathVariable Integer id,@RequestBody Book book) {
		return bookService.update(book);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = "application/json")
	public void deletBook(@PathVariable Integer id) {
		bookService.delet(id);
	}
	
	@RequestMapping(value = "/detailed/{id}", method = RequestMethod.GET,produces = "application/json")
	public String getDetailedBook(@PathVariable Integer id) {
		return bookService.getDetailedBook(id);
	}

}
