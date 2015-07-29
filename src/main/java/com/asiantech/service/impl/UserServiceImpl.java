package com.asiantech.service.impl;

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
	public User save(User user) {
		if(getUserbyEmail(user.getEmail())==null){
		    User us =userRepository.save(user) ;
		    us.setPassword("");
		    return us;
		}
		return null;
	}

	@Override
	@Transactional
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	@Transactional
	public User getUserbyEmail(String email) {
		User getus=null;
		List<User> us= userRepository.findAll();
		for(User u:us){
			if(u.getEmail().equals(email)){
			  getus=u;	
			}
		return getus;
			
		}
		return null;
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
		if(user!=null){
		   userRepository.delete(user);
		    return user;
		}
		return null;
	}

	@Override
	@Transactional
	public User update(User user) {
		User us=userRepository.findOne(user.getId());
		us.setFirstname(user.getFirstname());
		us.setLastname(user.getLastname());
		us.setPassword(user.getPassword());
		return us;
	}

}
