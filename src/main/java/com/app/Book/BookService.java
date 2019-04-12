package com.app.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.app.Languages.Languages;
import com.app.Type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.BookName.BookName;
import com.app.BookName.BookNameService;
import com.app.Challan.Challan;
import com.app.Languages.LanguagesService;
import com.app.Type.TypeService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
@Transactional
public class BookService  {

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	LanguagesService languagesService;
	
	@Autowired
	TypeService typeService;
	
	@Autowired
	BookNameService bookNameService;
	
	ObjectMapper mapper = new ObjectMapper();
	
	public Book create(Book book) {
		return bookRepository.save(book);
	}

	public Book update(Book book) {
		return bookRepository.save(book);
	}

	public void delet(Integer id) {
		bookRepository.deleteById(id);
	}

	public Optional<Book> find(int id) {
		return bookRepository.findById(id);
	}

	public List<Book> getall() {
		List<Book> list = new ArrayList<>();
		bookRepository.findAll().forEach(list::add);
		for(Book book:list)
		{
			System.out.println(book.getName().getName());
		}
		return list;
	}
	
	public String getDetailedBook(int id) {
		Book book= find(id).get();
		JsonNode rootNode = mapper.valueToTree(book);
		JsonNode nameNode=mapper.valueToTree(bookNameService.find(book.getName().getId()));
		((ObjectNode) rootNode).set("name", nameNode);
		JsonNode langNode=mapper.valueToTree(languagesService.find(book.getLanguages().getId()));
		((ObjectNode) rootNode).set("languages", langNode);
		JsonNode typeNode=mapper.valueToTree(typeService.find(book.getType().getId()));
		((ObjectNode) rootNode).set("type", typeNode);
		return rootNode.toString();
	}
	
	public JsonNode getDetailedBookJSON(int id) {
		Book book= find(id).get();
		JsonNode rootNode = mapper.valueToTree(book);
		JsonNode nameNode=mapper.valueToTree(bookNameService.find(book.getName().getId()));
		((ObjectNode) rootNode).set("name", nameNode);
		JsonNode langNode=mapper.valueToTree(languagesService.find(book.getLanguages().getId()));
		((ObjectNode) rootNode).set("languages", langNode);
		JsonNode typeNode=mapper.valueToTree(typeService.find(book.getType().getId()));
		((ObjectNode) rootNode).set("type", typeNode);
		return rootNode;
	}
	
	public ArrayNode getAllBookInDetail() {
		List<Book> bookList = new ArrayList<>();
		ArrayNode bookArray = mapper.createArrayNode();
		bookRepository.findAll().forEach(bookList::add);
		
		for(Book book : bookList) {
			bookArray.add(getDetailedBookJSON(book.getId()));
		}
		
		System.out.println("get All book in detail");
		System.out.println(bookArray);
		return bookArray;
	}

	public Optional<Book> FindByNameTypeLang(BookName bn, Languages l, Type t){
		return bookRepository.FindByNameTypeLang(bn, l, t);
	}
		

}
