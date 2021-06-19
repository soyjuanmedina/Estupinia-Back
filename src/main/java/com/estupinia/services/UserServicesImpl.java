package com.estupinia.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.estupinia.models.Theme;
import com.estupinia.models.User;
import com.estupinia.repository.ThemeRepository;
import com.estupinia.repository.UserRepository;

@Service
public class UserServicesImpl implements UserServices{
	
	@Autowired 
	SimpUserRegistry simpUserRegistry;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ThemeRepository themeRepository;
	
	
	@Override
	public List<User> getConnectedUsers() {		
		Set<SimpUser> userSet = simpUserRegistry.getUsers();
		List<User> userList = new ArrayList<User>();
		for (SimpUser user : userSet) {
			User userComplet = userRepository.findUserByUsername(user.getName());
			if(userComplet != null) {
				userList.add(userComplet);
			}
		}
		return userList;
	}
	
	public List<User> getConnectedByTheme(Theme theme) {		
		Set<SimpUser> userSet = simpUserRegistry.getUsers();
		List<User> userList = new ArrayList<User>();
		for (SimpUser user : userSet) {
			User userComplet = userRepository.findUserByUsername(user.getName());
			if(userComplet != null) {
				userList.add(userComplet);
			}
		}
		return userList;
	}
	
//    @Scheduled(cron = "0 0/15 * * * *")
//	public void wakeupHeroku() {
//    	System.out.println("Despertando Heroku cada 15 minutos");
//    	themeRepository.findAll();
//	}
}
