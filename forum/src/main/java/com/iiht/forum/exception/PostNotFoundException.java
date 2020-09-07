package com.iiht.forum.exception;

public class PostNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3149606590630559956L;

	public PostNotFoundException(String message) {
		super(message);
	}
}