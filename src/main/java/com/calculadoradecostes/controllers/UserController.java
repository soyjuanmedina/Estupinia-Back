package com.calculadoradecostes.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;
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

import com.calculadoradecostes.models.AccountingNote;
import com.calculadoradecostes.models.Project;
import com.calculadoradecostes.models.User;
import com.calculadoradecostes.payload.request.LoginRequest;
import com.calculadoradecostes.payload.request.SignupRequest;
import com.calculadoradecostes.payload.response.JwtResponse;
import com.calculadoradecostes.repository.AccountingNoteRepository;
import com.calculadoradecostes.repository.ProjectRepository;
import com.calculadoradecostes.repository.UserRepository;
import com.calculadoradecostes.security.services.UserDetailsImpl;

@RestController
@RequestMapping("/user")
@PreAuthorize("isAuthenticated()")  
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	AccountingNoteRepository accountingNoteRepository;
	
	@PostMapping("/")
	public ResponseEntity<Optional<User>> getUser() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		Optional<User> user = userRepository.findByUsername(currentPrincipalName);

		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/save")
	public  ResponseEntity<Boolean> saveUser(@Valid @RequestBody User user) {
			
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Optional<User> currentUser = 
				userRepository.findByUsername(currentPrincipalName);
		
		if(user.getUsername() == currentUser.get().getUsername()) {
			userRepository.save(user);
			return ResponseEntity.ok(true);
		} else {
			return ResponseEntity.ok(false);
		}

		
	}
	
	@PostMapping("/save/project")
	public  ResponseEntity<Boolean> saveProjectToUser(@Valid @RequestBody Project project) {
			
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		Optional<User> currentUser = 
				userRepository.findByUsername(currentPrincipalName);
		
		project.getEsteemedCustomers().setProject(project);
		project.getCosts().setProject(project);
		
		for (AccountingNote income :project.getIncomes()) {
			income.setProject(project);
			accountingNoteRepository.save(income);
		}
		for (AccountingNote fixedcost :project.getCosts().getFixedcosts()) {
			fixedcost.setFixedcosts(project.getCosts());
			accountingNoteRepository.save(fixedcost);
		}
		for (AccountingNote variablecost :project.getCosts().getVariablescosts()) {
			variablecost.setVariablescosts(project.getCosts());
			accountingNoteRepository.save(variablecost);
		}
		
		project = projectRepository.save(project);
		
		if (currentUser.get().getProjects().contains(project)) {
			currentUser.get().getProjects().remove(project);
		}
		currentUser.get().getProjects().add(project);
		userRepository.save(currentUser.get());

		return ResponseEntity.ok(true);
	}
	
	@PostMapping("/delete/project")
	public  ResponseEntity<Boolean> deleteProjectFromUser(@Valid @RequestBody Project project) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Optional<User> currentUser = 
				userRepository.findByUsername(currentPrincipalName);
		
		for (Project myproject :currentUser.get().getProjects()) {
			System.out.println(myproject.getId());
			System.out.println(project.getId());
			if(myproject.getId().equals(project.getId())) {
				userRepository.deleteProjectFromUser(project.getId(), currentUser.get().getId());
				return ResponseEntity.ok(true);
			}
		}
		
		return ResponseEntity.ok(false);
				
	}
	

}
