package com.estupinia.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;

import com.estupinia.models.User;
import com.estupinia.repository.UserRepository;
import com.estupinia.services.UserServices;

@Service
public class UserServicesImpl implements UserServices{
	
	@Autowired 
	SimpUserRegistry simpUserRegistry;
	
	@Autowired
	UserRepository userRepository;
	
	
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
}
