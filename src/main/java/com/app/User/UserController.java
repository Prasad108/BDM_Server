package com.app.User;

import java.security.Principal;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.Center.Center;
import com.app.Role.Roles;
import com.app.security.message.SignUpForm;
@CrossOrigin
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
	
	@RolesAllowed("ROLE_ADMIN")
	@PostMapping("/update/{id}")
	public ResponseEntity<?> registerUser(@RequestBody SignUpForm signUpRequest,@PathVariable Integer id) {
		userService.update(signUpRequest,id);
		return ResponseEntity.ok(signUpRequest);
	}

}
