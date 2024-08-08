package com.tour.custom_exception_handler;

public class ApiException extends RuntimeException{
	
	public ApiException(String mesg) {
		super(mesg);
	}
	
	
}
