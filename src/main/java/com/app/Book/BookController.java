package com.app.Book;

import com.app.BookName.BookName;
import com.app.Exceptions.ResourceNotFound;
import com.app.Languages.LanguagesService;
import com.app.Type.TypeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/book") 
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private LanguagesService languagesService;
	
	@Autowired
	private TypeService typeService;
	
	ObjectMapper mapper = new ObjectMapper();
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Book> getAllBooks() {
	return bookService.getall();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@RolesAllowed("ROLE_ADMIN")
	public Book getBook(@PathVariable Integer id) {
		return bookService.find(id).orElseThrow(()->  new ResourceNotFound("Book Not Found"));
	}
	
	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/", method = RequestMethod.PUT,produces = "application/json")
	public Book addBook(@RequestBody Book book) {
		return bookService.create(book);
	}
	
	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Book updateBook(@PathVariable Integer id,@RequestBody Book book) {
		return bookService.update(book);
	}
	
	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = "application/json")
	public void deletBook(@PathVariable Integer id) {
		bookService.delet(id);
	}
	
	@RequestMapping(value = "/detailed/{id}", method = RequestMethod.GET,produces = "application/json")
	public String getDetailedBook(@PathVariable Integer id) {
		return bookService.getDetailedBook(id);
	}
	
	@RequestMapping(value = "/getAllBookInDetail", method = RequestMethod.GET,produces = "application/json")
	public ArrayNode getAllBookInDetail() {
		return bookService.getAllBookInDetail();
	}

	@RequestMapping(value = "/findBook/{nameId}/{bookId}/{typeId}", method = RequestMethod.GET, produces = "application/json")
	public ArrayNode findBook(@PathVariable Integer nameId, @PathVariable Integer bookId, @PathVariable Integer typeId) {
		BookName bn = new BookName();
		bn.setId(bookId);
		return bookService.FindByNameTypeLang();
	}

}
