package com.tweetapp.dao;

import java.util.List;

import com.tweetapp.entity.User;

public interface UserDao {
	
	User registerUser(User user);
	User getUserByUserId(String userId);
	List<User> getAllUsers();

}
