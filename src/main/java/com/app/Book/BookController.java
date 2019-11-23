package com.app.Book;

import com.app.BookName.BookName;
import com.app.Exceptions.ResourceNotFound;
import com.app.Languages.Languages;
import com.app.Languages.LanguagesService;
import com.app.Type.Type;
import com.app.Type.TypeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {

    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private BookService bookService;
    @Autowired
    private LanguagesService languagesService;
    @Autowired
    private TypeService typeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Book> getAllBooks() {
        return bookService.getall();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @RolesAllowed("ROLE_ADMIN")
    public Book getBook(@PathVariable Integer id) {
        return bookService.find(id).orElseThrow(() -> new ResourceNotFound("Book Not Found"));
    }

    @RolesAllowed("ROLE_ADMIN")
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces = "application/json")
    public Book addBook(@RequestBody Book book) {
        return bookService.create(book);
    }

    @RolesAllowed("ROLE_ADMIN")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Book updateBook(@PathVariable Integer id, @RequestBody Book book) {
        return bookService.update(book);
    }

    @RolesAllowed("ROLE_ADMIN")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public void deletBook(@PathVariable Integer id) {
        bookService.delet(id);
    }

    @RequestMapping(value = "/detailed/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getDetailedBook(@PathVariable Integer id) {
        return bookService.getDetailedBook(id);
    }

    @RequestMapping(value = "/getAllBookInDetail", method = RequestMethod.GET, produces = "application/json")
    public ArrayNode getAllBookInDetail() {
        return bookService.getAllBookInDetail();
    }

    @RequestMapping(value = "/findBook/{nameId}/{langId}/{typeId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity findBook(@PathVariable Integer nameId, @PathVariable Integer langId, @PathVariable Integer typeId) {

        BookName bn = new BookName();
        bn.setId(nameId);
        Languages language = new Languages();
        language.setId(langId);
        Type type = new Type();
        type.setId(typeId);
        Optional<Book> book = bookService.FindByNameTypeLang(bn, language, type);
        return book.map(value -> new ResponseEntity(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity(HttpStatus.ALREADY_REPORTED));
    }

    @RequestMapping(value = "/addNewBook", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createNewBook(@Valid @RequestBody Book book) {

        Optional<Book> bookCreated = bookService.FindByNameTypeLang(book.getName(), book.getLanguages(), book.getType());
        if (bookCreated.isPresent()) {
            return new ResponseEntity("Book already present", HttpStatus.CONFLICT);
        } else {
            bookService.create(book);
            return new ResponseEntity(HttpStatus.CREATED);
        }
    }

}
