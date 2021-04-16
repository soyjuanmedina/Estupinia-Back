package com.calculadoradecostes.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calculadoradecostes.models.Params;
import com.calculadoradecostes.models.Project;
import com.calculadoradecostes.models.User;
import com.calculadoradecostes.repository.ProjectRepository;
import com.calculadoradecostes.services.EmailService;

@RestController
@RequestMapping("/utilities")
public class UtilitiesController {
	

	@Autowired
	EmailService emailService;
	
	@PostMapping("/sendMail")
	public  ResponseEntity<Boolean> sendMail(@RequestBody Params params) {
		emailService.sendEmail(params);
		return ResponseEntity.ok(true);
	}

}
