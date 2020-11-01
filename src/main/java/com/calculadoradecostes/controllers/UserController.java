package com.calculadoradecostes.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calculadoradecostes.models.Project;
import com.calculadoradecostes.models.User;
import com.calculadoradecostes.payload.request.LoginRequest;
import com.calculadoradecostes.payload.request.SignupRequest;
import com.calculadoradecostes.payload.response.JwtResponse;
import com.calculadoradecostes.repository.UserRepository;
import com.calculadoradecostes.security.services.UserDetailsImpl;

@RestController
@Transactional
@RequestMapping("/user")
@PreAuthorize("isAuthenticated()")  
public class UserController {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/")
	public ResponseEntity<Optional<User>> getUser() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		Optional<User> user = userRepository.findByUsername(currentPrincipalName);

		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/save")
	public  ResponseEntity<Boolean> saveUser(@Valid @RequestBody User user) {
			
		entityManager.merge(user);

		return ResponseEntity.ok(true);
	}
	
	

}
