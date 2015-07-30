package com.asiantech.service;

import java.util.List;

import com.asiantech.entity.User;

public interface UserService {
	public User save(User user) throws Exception;
	public List<User> getUsers();
	public User getUserbyEmail(String email);
	public User findById(String id);
	public User delete(String id);
	public User update(User user);

}
