package com.app.BookName;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
@Transactional
public class BookNameService  {

	@Autowired
	BookNameRepository bookNameRepository;
	
	ObjectMapper mapper = new ObjectMapper();
	
	public BookName create(BookName bookName) {
		return bookNameRepository.save(bookName);
	}

	public BookName update(BookName bookName) {
		return bookNameRepository.save(bookName);
	}

	public void delet(Integer id) {
		bookNameRepository.deleteById(id);
	}

	public BookName find(int id) {
		return bookNameRepository.findById(id).get();
	}

	private JsonNode getBookName(BookName bookName)
	{
		JsonNode rootNode = mapper.valueToTree(bookName);
		((ObjectNode) rootNode).set("name",mapper.valueToTree(bookName.getName()));
		return rootNode;
	}
	
	public ArrayNode getall() {
		List<BookName> list = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode bookArray = mapper.createArrayNode();
		bookNameRepository.findAll().forEach(list::add);
		for(BookName name:list)
		{
			bookArray.add(getBookName(name));
		}
		return bookArray;
	}
	
	public List<BookName> getAllBookNameOfUsersInventory(String userName) {
		List<BookName> list = bookNameRepository.getAllBookNameOfUsersInventory(userName).orElseThrow(() -> 
		  new RuntimeException("Error in findin users Bookname List -> username : " + userName));
		return list;
	}

}
