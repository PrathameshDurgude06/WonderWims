package com.tour.custom_exception;

public class InvalidCredentialsException extends RuntimeException{
	public InvalidCredentialsException(String mesg) {
		super(mesg);
	}
}