package com.app.Service;

import java.util.List;

import com.app.pojo.User;

public interface UserService {
	public void create(User user);
	public void update(User user);
	public void delet(User user);
	public User find(int id);
	public List<User> getall();
}
