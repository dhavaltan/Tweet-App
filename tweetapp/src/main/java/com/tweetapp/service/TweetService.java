package com.tweetapp.service;

import java.util.List;

import com.tweetapp.entity.Tweet;
import com.tweetapp.entity.User;

public interface TweetService {
	
	Tweet saveTweet(Tweet tweet);
	
	List<Tweet> getTweetByUserId(User user);
	
	List<Tweet> getAllTweets();
}
