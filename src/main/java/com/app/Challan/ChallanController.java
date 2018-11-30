package com.app.Challan;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.Book.BookController;
import com.app.CbDetails.CbDetails;
import com.google.gson.Gson;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController
@RequestMapping("/challan") 
public class ChallanController {

	@Autowired
	ChallanService challanService;
	
	BookController bookController;


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Challan> getAllChallans() {
		return challanService.getall();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Challan getChallan(@PathVariable Integer id) {
		return challanService.find(id);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public void addChallan(@RequestBody Challan challan) {
		challanService.create(challan);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updateChallan(@PathVariable Integer id,@RequestBody Challan challan) {
		challanService.update(challan);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deletChallan(@PathVariable Integer id) {
		challanService.delet(id);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public List<Challan> getUserChallans(@PathVariable Integer id) {
		return challanService.findByUserByIssuedTo(id);
	}
	
	@RequestMapping(value = "/detailed/{id}", method = RequestMethod.GET)
	public Challan getDetailedChallan(@PathVariable Integer id) {
		Challan ch=challanService.find(id);
		
		List<CbDetails> chi=ch.getCbDetailses().stream()
		.map(e->{
			e.setBook(bookController.getBook(e.getBook().getId()));
			return e;})
		.collect(Collectors.toList());
		
		chi.forEach(System.out::println);;
		
		return challanService.find(id);
	}

}
