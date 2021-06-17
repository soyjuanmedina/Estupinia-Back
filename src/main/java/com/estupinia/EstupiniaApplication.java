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
import com.estupinia.models.Theme;
import com.estupinia.repository.RoleRepository;
import com.estupinia.repository.ThemeRepository;

@SpringBootApplication
@EnableScheduling
public class EstupiniaApplication {
	
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	ThemeRepository themeRepo;
	
	private boolean rolesIfNotExists = true;
	private boolean themeIfNotExists= true;
	
	public static void main(String[] args) {
		SpringApplication.run(EstupiniaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(RoleRepository roleRepository, ThemeRepository themeRepository) {
		return (args) -> {
			initialChargeValidator();
			if(rolesIfNotExists) {
				roleRepository.save(new Role(ERole.FREE_USER));
				roleRepository.save(new Role(ERole.PAY_USER));
				roleRepository.save(new Role(ERole.ADMIN));				
			}
			if(themeIfNotExists) {
				themeRepository.save(new Theme("Actualidad"));
				themeRepository.save(new Theme("Corazon"));
				themeRepository.save(new Theme("Ciencia"));
			}
		};
	}
	
	private void initialChargeValidator() {
		
		List<Role> findAllRoles = roleRepo.findAll();
		List<Theme> findAllTheme = themeRepo.findAll();
		
		if(findAllRoles.size() > 0) {
			rolesIfNotExists = false;
		}
		if(findAllTheme.size() > 0) {
			themeIfNotExists = false;
		}
	}
}
