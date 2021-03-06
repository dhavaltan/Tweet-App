package com.tweetapp.exception;

public class IncorrectPasswordException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IncorrectPasswordException(String message) {
		super(message);
	}
}
