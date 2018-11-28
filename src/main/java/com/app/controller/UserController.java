package com.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user") 
public class UserController {
	
//	Gson gson = new Gson();
//		
//	@Autowired
//	LanguagesService languagesService;
//	
//	@Autowired
//	CbDetailsService cbDetailsService;
//	
//	@Autowired
//	CenterService bookService;
//	
//	@Autowired
//	CenterService centerService;
//	
//	@Autowired
//	ChallanService challanService;
//	
//	@Autowired
//	InventryService inventryService;
//	
//	@Autowired
//	RolesService rolesService;
//	
//	@Autowired
//	TypeService typeService;
//	
//	@Autowired
//	UserService userService;
//	
//	
//	@RequestMapping(value = "/getAllLanguages", method = RequestMethod.GET)
//	public String getAllLanguages() {
//		System.out.println("**********inside getAllLanguages controller**********");
//		return gson.toJson(languagesService.getall());
//	}
//	
//	@RequestMapping(value = "/getAllBooks", method = RequestMethod.GET)
//	public String getAllBooks() {
//		System.out.println("**********inside getAllBooks controller**********");
//		return gson.toJson(bookService.getall());
//	}
//	
//	@RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
//	public String getAllUser() {
//		System.out.println("**********inside getAllUser controller**********");
//		return gson.toJson(userService.getall());
//	}

}
