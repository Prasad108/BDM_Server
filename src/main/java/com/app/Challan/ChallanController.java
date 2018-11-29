package com.app.Challan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
@RequestMapping("/challan") 
public class ChallanController {

	@Autowired
	ChallanService challanService;


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

}
