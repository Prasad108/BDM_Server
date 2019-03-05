package com.app.CbDetails;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.Book.Book;
import com.app.Book.BookService;
import com.app.BookName.BookName;
import com.app.Challan.ChallanRepository;
import com.app.Challan.ChallanService;
import com.app.Inventry.Inventry;
import com.app.Inventry.InventryService;
import com.app.Languages.Languages;
import com.app.Type.Type;
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
	
	@Autowired
	ChallanRepository challanRepository;
	
	@Autowired
	ChallanService challanService;
	
	@Autowired
	InventryService inventryService;
	
	ObjectMapper mapper = new ObjectMapper();
	
	public CbDetails create(CbDetails cbDetails) {
		return cbDetailsRepository.save(cbDetails);
	}

	public CbDetails update(CbDetails cbDetails) {
		return cbDetailsRepository.save(cbDetails);
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

	public String getCbDetailFromChallanWithRequestedNameLangType(Integer challan, Integer name, Integer lang,
			Integer type, String username) throws IOException {
		BookName bn= new BookName();
		bn.setId(name);
		Languages l =new Languages();
		l.setId(lang);
		Type t= new Type();
		t.setId(type);
//		CbDetails cb
		Optional<CbDetails> optional=cbDetailsRepository.getCbDetailFromChallanWithRequestedNameLangType(challan,bn,l,t);
		if(optional.isPresent()) {
			CbDetails cb=optional.get();
			JsonNode  result=	mapper.readTree(detaliedCbDetail(cb.getId()));
			((ObjectNode) result).set("found",mapper.convertValue(true, JsonNode.class));
			return result.toString();
		}else {
			final ObjectNode result = mapper.createObjectNode();
			((ObjectNode) result).set("found",mapper.convertValue(false, JsonNode.class));
			List<Object[]> queryResult=inventryService.findByUserCenterLangNameandType(bn,l,t,username)
					.orElseThrow(() -> new RuntimeException("Sorry there is no such book in inventry!"));
			Inventry i=new Inventry();
			Book b= new Book();
			for (Object o[] : queryResult) {
				i=(Inventry) o[0];
				b=(Book) o[1];
			}
			((ObjectNode) result).set("bookId",mapper.convertValue(i.getBook().getId(), JsonNode.class));
			((ObjectNode) result).set("inventryStock",mapper.convertValue(i.getQuantity(), JsonNode.class));
			((ObjectNode) result).set("rate",mapper.convertValue(b.getPrice(), JsonNode.class));
			return result.toString();
		}
	}

	public String getInwardCbDetailFromChallanWithRequestedNameLangType(Integer challan, Integer name, Integer lang,
																  Integer type) throws IOException {
		BookName bn= new BookName();
		bn.setId(name);
		Languages l =new Languages();
		l.setId(lang);
		Type t= new Type();
		t.setId(type);
//		CbDetails cb
		Optional<CbDetails> optional=cbDetailsRepository.getCbDetailFromChallanWithRequestedNameLangType(challan,bn,l,t);
		if(optional.isPresent()) {
			CbDetails cb=optional.get();
			JsonNode  result=	mapper.readTree(detaliedCbDetail(cb.getId()));
			((ObjectNode) result).set("found",mapper.convertValue(true, JsonNode.class));
			return result.toString();
		}else {
			final ObjectNode result = mapper.createObjectNode();
			((ObjectNode) result).set("found",mapper.convertValue(false, JsonNode.class));
			Optional<Book> queryResult=bookService.FindByNameTypeLang(bn,l,t);
			Book b=queryResult.orElseThrow(() -> new RuntimeException("Sorry there is no such book all Records!"));
			((ObjectNode) result).set("bookId",mapper.convertValue(b.getId(), JsonNode.class));
			((ObjectNode) result).set("rate",mapper.convertValue(b.getPrice(), JsonNode.class));
			return result.toString();
		}
	}

}
