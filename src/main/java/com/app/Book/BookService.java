package com.app.Book;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.Languages.LanguagesService;
import com.app.Type.TypeService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	ObjectMapper mapper = new ObjectMapper();
	
	public void create(Book book) {
		bookRepository.save(book);
	}

	public void update(Book book) {
		bookRepository.save(book);
	}

	public void delet(Integer id) {
		bookRepository.deleteById(id);
	}

	public Book find(int id) {
		return bookRepository.findById(id).get();
	}

	public List<Book> getall() {
		List<Book> list = new ArrayList<>();
		bookRepository.findAll().forEach(list::add);
		return list;
	}
	
	public String getDetailedBook(int id) {
		Book book= find(id);
		JsonNode rootNode = mapper.valueToTree(book);
		JsonNode lqngNode=mapper.valueToTree(languagesService.find(book.getLanguages().getId()));
		((ObjectNode) rootNode).set("languages", lqngNode);
		JsonNode typeNode=mapper.valueToTree(typeService.find(book.getType().getId()));
		((ObjectNode) rootNode).set("type", typeNode);
		
		return rootNode.toString();
	}

}
