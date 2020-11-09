package com.calculadoradecostes.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calculadoradecostes.models.Project;
import com.calculadoradecostes.repository.ProjectRepository;

@RestController
@RequestMapping("/project")
public class ProjectController {
	

	@Autowired
	ProjectRepository projectRepository;
	
	@PostMapping("/get")
	public  ResponseEntity<Boolean> getProject() {
		return ResponseEntity.ok(true);
	}
	
	@PostMapping("/get/hostelery/examples")
	public  ResponseEntity<List<Project>> getHosteleryProjectsExamples() {
		
		List<Project> examples = projectRepository.findByType("HosteleryExample");
		return ResponseEntity.ok(examples);
	}

	@PostMapping("/save")
	public  ResponseEntity<Boolean> saveFoo(@Valid @RequestBody Project project) {
		
		projectRepository.save(project);
		return ResponseEntity.ok(true);
	}

}
