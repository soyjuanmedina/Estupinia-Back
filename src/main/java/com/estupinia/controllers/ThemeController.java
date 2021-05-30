package com.estupinia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estupinia.models.Theme;
import com.estupinia.repository.ThemeRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/theme")
public class ThemeController {
	
	@Autowired
	ThemeRepository themeRepository;
	
	@PostMapping("/get")
	public ResponseEntity<List<Theme>> getUser() {
		List<Theme> theme = themeRepository.findAll();
		return ResponseEntity.ok(theme);
	}

}
