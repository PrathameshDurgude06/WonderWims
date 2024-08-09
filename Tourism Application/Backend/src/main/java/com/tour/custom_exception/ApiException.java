package com.tour.custom_exception;

public class ApiException extends RuntimeException{
	
	public ApiException(String mesg) {
		super(mesg);
	}
	
	
}
