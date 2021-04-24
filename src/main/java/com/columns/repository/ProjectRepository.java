package com.columns.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.columns.models.Project;
import com.columns.models.User;

@Transactional 
public interface ProjectRepository extends CrudRepository<Project, Long>{
	Optional<Project> findById(Long id);
	
	List<Project> findByType(String type);
	
	Project findByUuid(String uuid);
	
}
