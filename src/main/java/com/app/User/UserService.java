package com.app.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService  {

	@Autowired
	UserRepository userRepository;
	
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
	
	

}
