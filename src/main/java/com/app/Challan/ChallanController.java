package com.app.Challan;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.Book.BookService;
import com.app.User.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/challan") 
public class ChallanController {

	@Autowired
	ChallanService challanService;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	UserService userService;
	
	
	
	ObjectMapper mapper = new ObjectMapper();


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Challan> getAllChallans() {
		return challanService.getall();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Challan getChallan(@PathVariable Integer id) {
		return challanService.find(id);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public Challan addChallan(@RequestBody Challan challan) {
		return challanService.create(challan);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Challan updateChallan(@PathVariable Integer id,@RequestBody Challan challan) {
		return challanService.update(challan);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = "application/json")
	public void deletChallan(@PathVariable Integer id) {
		challanService.delet(id);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public List<Challan> getUserChallans(@PathVariable Integer id) {
		return challanService.findByUserByIssuedTo(id);
	}
	
	@RequestMapping(value = "/detailed/{id}", method = RequestMethod.GET,produces = "application/json")
	public String getDetailedChallan(@PathVariable Integer id) throws IOException {
		return challanService.getDetailedChallan(id);
	}

}
