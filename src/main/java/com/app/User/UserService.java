package com.app.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.Role.Roles;
import com.app.Role.RolesRepository;
import com.app.security.message.SignUpForm;

@Service
@Transactional
public class UserService  {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RolesRepository rolesRepository;
	
	public void create(User user) {
		userRepository.save(user);
	}

	public void update(User user) {
		userRepository.save(user);
	}

	public void delet(Integer id) {
		userRepository.deleteById(id);
	}

	public User find(int id) {
		return userRepository.findById(id).get();
	}

	public List<User> getall() {
		List<User> list = new ArrayList<>();
		userRepository.findAll().forEach(list::add);
		return list;
	}
	
	public List<User> allUserOfcurrentUsersCenter(String username){
		return userRepository.allUserOfcurrentUsersCenter(username);
	}

	public List<User> getUsersOfCenterByCenterId(Integer id) {
		return userRepository.getUsersOfCenterByCenterId(id);
	}

	public User getCurrentUserDetails(String name) {
		return userRepository.findByUsername(name).get();
	}

	public void update(SignUpForm signUpRequest, Integer id) {
		Roles userRole= rolesRepository.findById(signUpRequest.getRoles()).get();
		userRepository.updateUser(id,
				signUpRequest.getName(),
				signUpRequest.getEmail(),
				signUpRequest.getMob(),
				userRole,
				signUpRequest.getCounceller());
	}
	
	

}
