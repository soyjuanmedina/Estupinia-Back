package com.estupinia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.estupinia.repository.ThemeRepository;

public class ThemeServiceImpl implements ThemeService {
	
	@Autowired
	ThemeRepository themeRepository;
	
    @Scheduled(cron = "0/5 * * * * *")
	public void wakeupHeroku() {
    	System.out.println("Despertando Heroku cada 5 segundos");
    	themeRepository.findAll();
	}

}
