package com.columns.security.services;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.columns.models.User;
import com.columns.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}
	
//	@Scheduled(cron = "*/5 * * * * *")
//	public void resetUserArticles() {
//		Optional<User> standarUsers = userRepository.findBySubscription("standar");
////		for (user standaruser:standarusers) {
////			system.out.println(
////				      "resetuserarticles - " + system.currenttimemillis() / 1000);
////			}
//	    System.out.println(
//	      "resetUserArticles - " + System.currentTimeMillis() / 1000);
	//}

}