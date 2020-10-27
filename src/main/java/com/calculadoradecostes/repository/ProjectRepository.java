package com.calculadoradecostes.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.calculadoradecostes.models.Project;

@Transactional 
public interface ProjectRepository extends CrudRepository<Project, Long>{

}
