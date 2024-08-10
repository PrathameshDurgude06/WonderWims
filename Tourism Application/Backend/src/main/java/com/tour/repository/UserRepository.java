package com.tour.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tour.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	//derived query method
	Optional<User> findByEmail(String email);
	
	//derived query method
	boolean existsByEmail(String email);
	
	Optional<User> findByEmailAndPassword(String em,String pass);
}
