package com.tour.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tour.custom_exception.ApiException;
import com.tour.custom_exception.InvalidCredentialsException;
import com.tour.custom_exception.ResourceNotFoundException;
import com.tour.dto.SigninRequest;
import com.tour.dto.Signup;
import com.tour.entities.User;
import com.tour.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public Signup userRegistration(Signup reqDTO) {
		//DTO --> entity
		User user=mapper.map(reqDTO,User.class);
		System.out.println(user+"************");
		if(userRepository.existsByEmail(reqDTO.getEmail()))
			throw new ApiException("Email already exists !!!");
		
		user.setPassword(encoder.encode(user.getPassword()));
		return mapper.map(userRepository.save(user),Signup.class);
	}

	@Override
	public User authenticateUser(SigninRequest signinDto) throws RuntimeException {
		System.out.println(signinDto);
		User user = userRepository.findByEmailAndPassword(signinDto.getEmail(), signinDto.getPassword()).orElseThrow(
				() -> new InvalidCredentialsException("Invalid Email or Password!!!"));
		System.out.println(user);
		return mapper.map(user, User.class);
	}

	
	@Override
	public List<User> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}
	
	@Override
	public User getById(Long id) {
      User user=userRepository.findById(id).orElseThrow(
    		  ()->new ResourceNotFoundException("User not found !!!"));
	return user;
	}
	
	
	
	
}
