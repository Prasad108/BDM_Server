package com.app.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user") 
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<User> getAllUsers() {
	return userService.getall();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable Integer id) {
		return userService.find(id);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public void addUser(@RequestBody User user) {
		userService.create(user);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updateUser(@PathVariable Integer id,@RequestBody User user) {
		userService.update(user);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deletUser(@PathVariable Integer id) {
		userService.delet(id);
	}

}