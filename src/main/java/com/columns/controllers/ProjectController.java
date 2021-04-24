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

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/project")
public class ProjectController {
	

	@Autowired
	ProjectRepository projectRepository;
	
	@PostMapping("/get")
	public  ResponseEntity<Optional<Project>> getProject(@RequestBody Long id) {
		Optional<Project> project = projectRepository.findById(id);
		return ResponseEntity.ok(project);
	}
	
	@PostMapping("/getsharedproject")
	public  ResponseEntity<Project> getSharedProject(@RequestBody Params params) {
		String uuid = params.getUuid();
		Project project = projectRepository.findByUuid(uuid);
		return ResponseEntity.ok(project);
	}

	@PostMapping("/save")
	public  ResponseEntity<Project> saveProject(@Valid @RequestBody Project project) {	
		project = projectRepository.save(project);
		return ResponseEntity.ok(project);
	}
	
	@PostMapping("/delete")
	public  ResponseEntity<Boolean> deleteProject (@Valid @RequestBody Project project) {	
		projectRepository.delete(project);
		return ResponseEntity.ok(true);
	}
	
	@PostMapping("/get/examples")
	public  ResponseEntity<List<Project>> getProjectsExamples(@RequestBody Params params) {
		String type = params.getType();
		List<Project> examples = projectRepository.findByType(type);
		return ResponseEntity.ok(examples);
	}

}
