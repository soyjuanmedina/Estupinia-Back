package com.columns.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.columns.models.Params;
import com.columns.models.Project;
import com.columns.models.User;
import com.columns.repository.ProjectRepository;
import com.columns.services.EmailService;

@RestController
@RequestMapping("/utilities")
@CrossOrigin(origins = "*")
public class UtilitiesController {
	

	@Autowired
	EmailService emailService;
	
	@PostMapping("/sendMail")
	public  ResponseEntity<Boolean> sendMail(@RequestBody Params params) {
		emailService.sendEmail(params);
		return ResponseEntity.ok(true);
	}

}
