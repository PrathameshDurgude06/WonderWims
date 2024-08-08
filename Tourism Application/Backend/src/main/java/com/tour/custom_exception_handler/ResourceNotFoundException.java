package com.tour.custom_exception_handler;

public class ResourceNotFoundException extends RuntimeException{
	public ResourceNotFoundException(String mesg) {
		super(mesg);
	}
}
