package com.estupinia.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estupinia.models.Params;
import com.estupinia.models.User;
import com.estupinia.repository.UserRepository;
import com.estupinia.services.UserServices;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepository;	
	
	@Autowired
	UserServices userServices;
	
	@PostMapping("/get")
	public ResponseEntity<Optional<User>> getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Optional<User> user = userRepository.findByUsername(currentPrincipalName);
		return ResponseEntity.ok(user);
	}

	@PostMapping("/update")
	public ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Optional<User> currentUser = userRepository.findByUsername(currentPrincipalName);
		if (user.getPassword() == null || user.getPassword().isEmpty()) {
			String currentPassword = currentUser.get().getPassword();
			user.setPassword(currentPassword);
		}
		if (user.getUsername().equals(currentUser.get().getUsername())) {
			user = userRepository.save(user);
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.ok(null);
		}
	}

	@PostMapping("/confirmemail")
	public ResponseEntity<Boolean> confirmemail(@Valid @RequestBody Params params) {
		String uuid = params.getUuid();
		Optional<User> user = userRepository.findByUuid(uuid);
		if (user.isPresent()) {
			user.get().setActive(true);
			userRepository.save(user.get());
			return ResponseEntity.ok(true);
		} else {
			return ResponseEntity.ok(false);
		}

	}
	
	@PostMapping("/getconnected")
	public ResponseEntity<List<User>> getConnected() {
	
		final List<User> userList = userServices.getConnectedUsers();
		
		return ResponseEntity.ok(userList);
	}

}
