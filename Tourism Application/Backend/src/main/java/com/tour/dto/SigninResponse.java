package com.tour.dto;

import com.tour.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SigninResponse {
	
	private Long userId;
	private String jwt;
	private String mesg;
	private Role role;
}

