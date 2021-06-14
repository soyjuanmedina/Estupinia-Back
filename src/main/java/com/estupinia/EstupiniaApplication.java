package com.estupinia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.estupinia.models.ERole;
import com.estupinia.models.Role;
import com.estupinia.repository.RoleRepository;

@SpringBootApplication
@EnableScheduling
public class EstupiniaApplication {
	
	@Autowired
	RoleRepository repo;
	
	private boolean isFirst = true;
	
	public static void main(String[] args) {
		SpringApplication.run(EstupiniaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(RoleRepository roleRepository) {
		return (args) -> {
			rolesValidator();
			if(isFirst) {
				roleRepository.save(new Role(ERole.FREE_USER));
				roleRepository.save(new Role(ERole.PAY_USER));
				roleRepository.save(new Role(ERole.ADMIN));
			}
		};
	}
	
	private void rolesValidator() {
		List<Role> findAllRoles = repo.findAll();
		if(findAllRoles.size() > 0) {
			isFirst = false;
		}
	}
}
