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

@RestController
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
	
	@PostMapping("/get/hostelery/examples")
	public  ResponseEntity<List<Project>> getHosteleryProjectsExamples() {
		
		List<Project> examples = projectRepository.findByType("HosteleryExample");
		return ResponseEntity.ok(examples);
	}

}
