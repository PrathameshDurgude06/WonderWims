package com.tour.service;

import java.util.List;

import com.tour.dto.SigninRequest;
import com.tour.dto.Signup;
import com.tour.entities.User;

public interface UserService {
	
	//sign up method
	Signup userRegistration(Signup reqDTO);
	
	User authenticateUser(SigninRequest signinDTO);
	
	List<User> getAllUsers();

	User getById(Long id);

}
