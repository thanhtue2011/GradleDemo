package com.asiantech.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.asiantech.entity.User;
@Component
public interface UserRepository extends MongoRepository<User, String>{
	
	public User getUserbyEmail(String email);
} 
