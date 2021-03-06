package com.tweetapp;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tweetapp.entity.Tweet;
import com.tweetapp.entity.User;
import com.tweetapp.exception.IncorrectPasswordException;
import com.tweetapp.exception.UserNotFoundException;
import com.tweetapp.service.TweetService;
import com.tweetapp.service.UserService;

@SpringBootApplication
public class TweetappApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private TweetService tweetService;

	public static void main(String[] args) {
		SpringApplication.run(TweetappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		try (Scanner scanner = new Scanner(System.in);) {
			String choice;
			String insideChoice = "";
			do {
				System.out.println("Enter 1.Register\n 2.Login\n3.Forgot Password\n4.Exit");
				//scanner.nextLine();
				choice = scanner.nextLine();
				User user = new User();

				switch (choice) {
				case "1":
					System.out.println("Enter Userid (email)");
					user.setUserId(scanner.nextLine());
					System.out.println("Enter Name:");
					user.setName(scanner.nextLine());
					System.out.println("Enter Password");
					user.setPassword(scanner.nextLine());
					this.userService.registerUser(user);
					break;
				case "2":
					System.out.println("Enter Userid (email)");
					final String userId = scanner.nextLine();

					System.out.println("Enter Password");

					String password = scanner.nextLine();
					user = this.userService.getUserByUserId(userId);
					if (user == null) {
						throw new UserNotFoundException("Incorrect credentials");
					}
					if (user.getPassword().equals(password)) {
						user.setLoggedIn(true);
						this.userService.registerUser(user);
						do {
							
							System.out.println(
									"1.Post a Tweet\n2.View my tweets\n3.View all tweets\n4.View all users\n5.Reset password\n6.Logout");
							insideChoice = scanner.nextLine();
							switch (insideChoice) {
							case "1":
								final Tweet tweet = new Tweet();
								tweet.setUserId(user);
								System.out.println("Enter tweet description");
								tweet.setTweetDescription(scanner.nextLine());
								tweetService.saveTweet(tweet);
								break;
							case "2":
								List<Tweet> tweets = this.tweetService.getTweetByUserId(user);
								tweets.stream().forEach((tweetElement) -> {
									System.out.println(tweetElement.getTweetDescription());
								});
								break;
							case "3":
								tweets = tweetService.getAllTweets();
								tweets.stream().forEach((tweetElement) -> {
									System.out.println(tweetElement.getTweetDescription());
								});
								break;
							case "4":
								List<User> users = userService.getAllUsers();
								users.stream().forEach((userElement) -> {
									System.out.println(userElement.getName());
								});
								break;
							case "5":
								System.out.println("Enter Current Password");
								String currentPassword = scanner.nextLine();
								if (user.getPassword().equalsIgnoreCase(currentPassword.trim())) {
									System.out.println("Enter new Password");
									String newPassword = scanner.nextLine();
									user.setPassword(newPassword);
									this.userService.registerUser(user);
									System.out.println("You have successfully reset your password");
								} else {
									throw new IncorrectPasswordException("Incorrect credentials");
								}

								break;
							case "6":
								user.setLoggedIn(false);
								System.out.println();
								System.out.println("Logged Out");
								System.out.println();
								this.userService.registerUser(user);
								break;
							default:
								break;
							}
						} while (!insideChoice.equals("6"));
					} else {
						throw new IncorrectPasswordException("Incorrect credentials");
					}
					break;
				case "3":
					System.out.println("Enter Userid");
					user = this.userService.getUserByUserId(scanner.nextLine());
					System.out.println("Enter New Password");
					user.setPassword(scanner.nextLine());
					this.userService.registerUser(user);
					System.out.println("You have successfully changed your password");
					break;
				default:
					break;
				}
			} while (!choice.equals("4"));

		}

	}

}
