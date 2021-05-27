package com.estupinia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estupinia.models.Params;
import com.estupinia.services.EmailService;

@RestController
@RequestMapping("/utilities")
@CrossOrigin(origins = "*")
public class UtilitiesController {

	@Autowired
	EmailService emailService;

	@PostMapping("/sendMail")
	public ResponseEntity<Boolean> sendMail(@RequestBody Params params) {

		String content = "Nombre del usuario: " + params.getName() + " - Mail del usuario: " + params.getEmail()
				+ " - Mensaje: " + params.getMessage();
		emailService.sendEmailTool(content, "soyjuanmedina@gmail.com", "Mensaje desde coolumns");
		return ResponseEntity.ok(true);
	}

	@GetMapping("/wakeup")
	public ResponseEntity<Boolean> Wakeup() {
		return ResponseEntity.ok(true);
	}

}
