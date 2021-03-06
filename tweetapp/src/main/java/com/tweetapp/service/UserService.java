package com.tweetapp.service;

import java.util.List;

import com.tweetapp.entity.User;

public interface UserService {
	
	User registerUser(User user);
	User getUserByUserId(String userId);
	List<User> getAllUsers();

}
