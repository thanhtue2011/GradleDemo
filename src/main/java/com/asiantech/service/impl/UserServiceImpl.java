package com.asiantech.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asiantech.entity.User;
import com.asiantech.repository.UserRepository;
import com.asiantech.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User save(User user)throws Exception {
		if(user == null){
		    throw new IllegalArgumentException("user could not empty");
		}
		User userCheck = getUserbyEmail(user.getEmail());
		if(userCheck != null)
		{
		    throw new RuntimeException("user duplicate");
		}
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public List<User> getUsers() {
		List<User> us=userRepository.findAll();
		List<User> listuser = new ArrayList<User>();
		for(User u:us){
			u.setPassword("");
			listuser.add(u);
		}
		return listuser ;
	}

	@Override
	@Transactional
	public User getUserbyEmail(String email) {
		User getus=null;
		List<User> us= userRepository.findAll();
		for(User u:us){
			if(u.getEmail().equals(email))
			  getus=u;	
			}
		return getus;
	}

	@Override
	@Transactional
	public User findById(String id) {
		return userRepository.findOne(id);
	}

	@Override
	@Transactional
	public User delete(String id) {
		User user= userRepository.findOne(id);
		if(user!=null)
		   userRepository.delete(user);
		return user;
	}

	@Override
	@Transactional
	public User update(User user) {
		User us=userRepository.findOne(user.getId());
		if(us!=null){
		us.setFirstname(user.getFirstname());
		us.setLastname(user.getLastname());
		us.setPassword(user.getPassword());
		}
		return us;
	}

}
