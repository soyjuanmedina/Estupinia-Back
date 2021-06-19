package com.estupinia.services;

import java.util.List;

import com.estupinia.models.Theme;
import com.estupinia.models.User;

public interface UserServices{
	
	List<User> getConnectedUsers();
	
	List<User> getConnectedByTheme(Theme theme);

	
}
