package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.Service.BookService;
import com.app.Service.CbDetailsService;
import com.app.Service.CenterService;
import com.app.Service.ChallanService;
import com.app.Service.InventryService;
import com.app.Service.LanguagesService;
import com.app.Service.RolesService;
import com.app.Service.TypeService;
import com.app.Service.UserService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/user") 
public class UserController {
	
	Gson gson = new Gson();
		
	@Autowired
	LanguagesService languagesService;
	
	@Autowired
	CbDetailsService cbDetailsService;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	CenterService centerService;
	
	@Autowired
	ChallanService challanService;
	
	@Autowired
	InventryService inventryService;
	
	@Autowired
	RolesService rolesService;
	
	@Autowired
	TypeService typeService;
	
	@Autowired
	UserService userService;
	
	
	
	@RequestMapping(value = "/getAllLanguages", method = RequestMethod.GET)
	public String showPerson() {
		System.out.println("**********inside getAllLanguages controller**********");
		return gson.toJson(languagesService.getall());
	}

}
