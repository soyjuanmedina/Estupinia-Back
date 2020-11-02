package com.calculadoradecostes.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.calculadoradecostes.models.Project;
import com.calculadoradecostes.models.User;

@Transactional 
public interface ProjectRepository extends CrudRepository<Project, Long>{
	Optional<Project> findById(Long id);
	
	Project save(Project project);
	
}
