package com.tour.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tour.custom_exception.ApiException;
import com.tour.dto.Signup;
import com.tour.entities.User;
import com.tour.repository.UserRepository;

public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userepRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public Signup userRegistration(Signup reqDTO) {
		//DTO --> entity
		User user=mapper.map(reqDTO,User.class);
		if(userepRepository.existsByEmail(reqDTO.getEmail()))
			throw new ApiException("Email already exists !!!");
		
		user.setPassword(encoder.encode(user.getPassword()));
		return mapper.map(userepRepository.save(user),Signup.class);
	}
	
	
	
	
}
