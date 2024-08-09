package com.tour.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ApiResponse {
	
	private HttpStatus status;
	
	private LocalDateTime timeStamp;
	
	private String message;
	
	public ApiResponse(HttpStatus status, String message) {
		super();
		this.status=status;
		this.message = message;
		this.timeStamp=LocalDateTime.now();
	}
	
	public ApiResponse(String message) {
		super();
		this.message = message;
		this.timeStamp=LocalDateTime.now();
	}
	
}

