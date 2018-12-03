package com.app.CbDetails;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.Book.BookService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
@Transactional
public class CbDetailsService  {

	@Autowired
	CbDetailsRepository cbDetailsRepository;
	
	@Autowired
	BookService bookService;
	
	ObjectMapper mapper = new ObjectMapper();
	
	public void create(CbDetails cbDetails) {
		cbDetailsRepository.save(cbDetails);
	}

	public void update(CbDetails cbDetails) {
		cbDetailsRepository.save(cbDetails);
	}

	public void delet(Integer id) {
		cbDetailsRepository.deleteById(id);
	}

	public CbDetails find(int id) {
		return cbDetailsRepository.findById(id).get();
	}

	public List<CbDetails> getall() {
		List<CbDetails> list = new ArrayList<>();
		cbDetailsRepository.findAll().forEach(list::add);
		return list;
	}
	
	public String detaliedCbDetail(int id) throws IOException {
		CbDetails cb=find(id);
		JsonNode rootNode = mapper.valueToTree(cb);
		String b=bookService.getDetailedBook(cb.getBook().getId());
		((ObjectNode) rootNode).set("book", mapper.readTree(b));			
		return rootNode.toString();
	}

}