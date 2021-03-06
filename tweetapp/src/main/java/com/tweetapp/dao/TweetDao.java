package com.tweetapp.dao;

import java.util.List;

import com.tweetapp.entity.Tweet;
import com.tweetapp.entity.User;

public interface TweetDao {

	Tweet saveTweet(Tweet tweet);
	List<Tweet> getTweetByUserId(User user);
	List<Tweet> getAllTweets();
}
