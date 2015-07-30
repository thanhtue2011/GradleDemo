package com.asiantech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asiantech.entity.User;
import com.asiantech.service.UserService;


@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/listuser")
	public List<User> saveUser(){
		return  userService.getUsers();
	}
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public User addUser(@RequestParam(value="firstname") String firstname,
			@RequestParam(value="lastname") String lastname,@RequestParam(value="email") String email,
			@RequestParam(value="password") String password){
		User user= new User(null,firstname,lastname,email,password);
		try {
			return userService.save(user);
		} catch (Exception e) {
			return null;
		}
		
	}
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	public List<User> deteteUser(@RequestParam(value="id") String id){
			userService.delete(id);
		return userService.getUsers();
		
	}
	
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public List<User> updateUser(@RequestParam(value="id") String id,@RequestParam(value="firstname") String firstname,
			@RequestParam(value="lastname") String lastname,@RequestParam(value="password") String password){
		User user = new User(id,firstname,lastname,null,password);
			userService.update(user);
		return userService.getUsers();
		
	}
}
