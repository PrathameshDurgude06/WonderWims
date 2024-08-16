package com.tour.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tour.dto.SigninRequest;
import com.tour.dto.SigninResponse;
import com.tour.dto.Signup;
import com.tour.entities.Role;
import com.tour.security.CustomUserDetails;
import com.tour.security.JwtUtils;
import com.tour.service.UserService;

import ch.qos.logback.classic.db.names.SimpleDBNameResolver;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(path="/users" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserSignInSignupController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager authMgr;
	
	// sign up
		/*
		 * URL - http://host:port/users/signup Method - POST request payload : sign up
		 * DTO (user details) resp : In case of success : Auth Resp DTO :mesg + JWT
		 * token + SC 201 In case of failure : SC 401
		 * 
		 */
		@PostMapping("/signup")
		public ResponseEntity<?> userSignup(@RequestBody Signup dto) {
			System.out.println("in sign up " + dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.userRegistration(dto));
		}

		/*
		 * URL - http://host:port/users/signin Method - POST request payload : Auth req
		 * DTO : email n password resp : In case of success : Auth Resp DTO :mesg + JWT
		 * token + SC 201 In case of failure : SC 401
		 * 
		 */
//		@RequestMapping( method=RequestMethod.POST  ,path= "/signin",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(path= "/signin")
		public ResponseEntity<?> authenticateUser(@RequestBody SigninRequest request) {
			System.out.println("in sign in" + request);
			// 1. create a token(implementation of Authentication i/f)
			// to store un verified user email n pwd
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(),
					request.getPassword());
			//2.  invoke auth mgr's authenticate method;
			Authentication verifiedToken = authMgr.authenticate(token);
				// => authentication n authorization successful !
			// 3. Extract the user's role from the Authentication object
		    Collection<? extends GrantedAuthority> authorities = verifiedToken.getAuthorities();
		    
			String role  =authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();
			Role userRole = role != null ? Role.valueOf(role) : null;
			
			// 4. Extract the userId from the Authentication object
		    CustomUserDetails userDetails = (CustomUserDetails) verifiedToken.getPrincipal();
		    Long userId = userDetails.getUserId();
		    
		    // 5. Create JWT and send it to the client in the response along with the user's role
		    SigninResponse resp = new SigninResponse(userId,jwtUtils.generateJwtToken(verifiedToken), "Successful Auth!!!!", userRole);

		    return ResponseEntity.status(HttpStatus.OK).body(resp);
		}
		
		@GetMapping("/view")
		public ResponseEntity<?> getAllUsers(){
			return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
		}

	}
