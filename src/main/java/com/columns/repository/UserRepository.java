package com.columns.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.columns.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {
	Optional<User> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM user_projects WHERE project_id = ?1 and user_id = ?2", nativeQuery = true)
	void deleteProjectFromUser(Long project_id, Long user_id);
}
