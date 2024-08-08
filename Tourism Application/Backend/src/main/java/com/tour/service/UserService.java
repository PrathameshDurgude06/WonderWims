package com.tour.service;

import com.tour.dto.Signup;

public interface UserService {
	
	//sign up method
	Signup userRegistration(Signup reqDTO);

}
