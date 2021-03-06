package com.tweetapp.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dao.TweetDao;
import com.tweetapp.entity.Tweet;
import com.tweetapp.entity.User;
import com.tweetapp.repository.TweetRepository;

@Service
public class TweetDaoImpl implements TweetDao{

	@Autowired
	private TweetRepository tweetRepository;
	
	@Override
	public Tweet saveTweet(final Tweet tweet) {
		return tweetRepository.save(tweet);
	}

	@Override
	public List<Tweet> getTweetByUserId(final User user) {
		return tweetRepository.findTweetsByUserId(user);
	}

	@Override
	public List<Tweet> getAllTweets() {
		return tweetRepository.findAll();
	}

}
