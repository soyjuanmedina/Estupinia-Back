package com.columns.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.columns.models.Costs;
import com.columns.models.Project;

@Transactional 
public interface CostsRepository extends CrudRepository<Costs, Long>{

}
