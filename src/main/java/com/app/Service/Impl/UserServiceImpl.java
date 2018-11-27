package com.app.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.DAO.UserDAO;
import com.app.Service.UserService;
import com.app.pojo.User;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDAO;

	@Override
	public void create(User user) {
		userDAO.create(user);

	}

	@Override
	public void update(User user) {
		userDAO.update(user);

	}

	@Override
	public void delet(User user) {
		userDAO.delet(user);

	}

	@Override
	public User find(int id) {
	
		return userDAO.find(id);
	}

	@Override
	public List<User> getall() {
		return userDAO.getall();
	}

}
