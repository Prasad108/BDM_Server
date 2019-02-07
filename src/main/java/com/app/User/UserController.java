package com.app.User;

import java.security.Principal;
import java.util.List;

import javax.annotation.security.RolesAllowed;

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
	
	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public void addUser(@RequestBody User user) {
		userService.create(user);
	}
	
	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updateUser(@PathVariable Integer id,@RequestBody User user) {
		userService.update(user);
	}
	
	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = "application/json")
	public void deletUser(@PathVariable Integer id) {
		userService.delet(id);
	}
	
	@RequestMapping(value = "/allUserOfcurrentUsersCenter", method = RequestMethod.GET)
	public List<User> allUserOfcurrentUsersCenter(Principal principal) {
		return userService.allUserOfcurrentUsersCenter(principal.getName());
	}
	
	
	@RequestMapping(value = "/getUsersOfCenterByCenterId/{id}", method = RequestMethod.GET)
	public List<User> getUsersOfCenterByCenterId(@PathVariable Integer id) {
		return userService.getUsersOfCenterByCenterId(id);
	}
	
	@RequestMapping(value = "/getCurrentUserDetails", method = RequestMethod.GET)
	public User getCurrentUserDetails(Principal principal) {
		return userService.getCurrentUserDetails(principal.getName());
	}

}
