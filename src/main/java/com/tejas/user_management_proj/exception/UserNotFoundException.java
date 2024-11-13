package com.tejas.user_management_proj.exception;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(Long id) {
		super("Could not Found user by id " + id);
	}
}
