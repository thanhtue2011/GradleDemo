package com.asiantech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@RequestMapping(value="/add")
	public User addUser(@RequestParam(value="firstname") String firstname,
			@RequestParam(value="lastname") String lastname,@RequestParam(value="email") String email,
			@RequestParam(value="password") String password){
		User user =null;
		if(userService.getUserbyEmail(email)==null){
			user= new User("",firstname,lastname,email,password);
			
		}
		return userService.save(user);
		
	}
	@RequestMapping(value="/delete")
	public List<User> deteteUser(@RequestParam(value="id") String id){
		User user = userService.findById(id);
		if(user!=null)
			userService.delete(id);
		return userService.getUsers();
		
	}
	
	@RequestMapping(value="/update")
	public List<User> updateUser(@RequestParam(value="id") String id,@RequestParam(value="firstname") String firstname,
			@RequestParam(value="lastname") String lastname,@RequestParam(value="password") String password){
		User user = userService.findById(id);
		if(user!=null)
			userService.update(user);
		return userService.getUsers();
		
	}
}
