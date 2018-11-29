package com.app.Book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
@RequestMapping("/book") 
public class BookController {
	
	@Autowired
	BookService bookService;
	
	Gson gson = new Gson();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Book> getAllBooks() {
	return bookService.getall();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Book getBook(@PathVariable Integer id) {
		return bookService.find(id);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public void addBook(@RequestBody Book book) {
		bookService.create(book);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updateBook(@PathVariable Integer id,@RequestBody Book book) {
		bookService.update(book);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deletBook(@PathVariable Integer id) {
		bookService.delet(id);
	}

}
