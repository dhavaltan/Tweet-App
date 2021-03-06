package com.tweetapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweetapp.entity.Tweet;
import com.tweetapp.entity.User;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long>{
	
	List<Tweet> findTweetsByUserId(User user);

}
