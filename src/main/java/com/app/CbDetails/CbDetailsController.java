package com.app.CbDetails;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.Book.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/cbDetails") 
public class CbDetailsController {
	
	@Autowired
	CbDetailsService cbDetailsService;
	
	@Autowired
	BookService bookService;
	
	
	ObjectMapper mapper = new ObjectMapper();
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<CbDetails> getAllCbDetailss() {
	return cbDetailsService.getall();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public CbDetails getCbDetails(@PathVariable Integer id) {
		return cbDetailsService.find(id);
	}
	
	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public CbDetails addCbDetails(@RequestBody CbDetails cbDetails) {
		return cbDetailsService.create(cbDetails);
	}
	
	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updateCbDetails(@PathVariable Integer id,@RequestBody CbDetails cbDetails) {
		cbDetailsService.update(cbDetails);
	}
	
	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = "application/json")
	public void deletCbDetails(@PathVariable Integer id) {
		cbDetailsService.delet(id);
	}
	
	@RequestMapping(value = "/detailed/{id}", method = RequestMethod.GET,produces = "application/json")
	public String detaliedCbDetail(@PathVariable Integer id) throws IOException {
		return cbDetailsService.detaliedCbDetail(id);
	}
	
	@RequestMapping(value = "/getCbDetailFromChallanWithRequestedNameLangType/{challan}/{name}/{lang}/{type}", method = RequestMethod.GET,produces = "application/json")
	public String getCbDetailFromChallanWithRequestedNameLangType(@PathVariable Integer challan,
			@PathVariable Integer name,@PathVariable Integer lang,@PathVariable Integer type,Principal principal) throws IOException {
		return cbDetailsService.getCbDetailFromChallanWithRequestedNameLangType(challan,name,lang,type,principal.getName());
	}

}
