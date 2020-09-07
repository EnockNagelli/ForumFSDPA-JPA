package com.iiht.forum.exception;

public class CommentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3156833551002324065L;

	public CommentNotFoundException(String message) {
		super(message);
	}
}