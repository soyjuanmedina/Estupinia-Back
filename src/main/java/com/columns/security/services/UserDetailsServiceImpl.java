package com.columns.security.services;

import java.util.List;

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

	@Scheduled(cron = "*/15 * * * * *")
	public void resetUserArticles() {
		System.out.println("Lanzado cron a medianoche");
		List<User> standarUsers = userRepository.findBySubscription("standar");
		for (User standarUser : standarUsers) {
			standarUser.setBuyedArticles("");
			standarUser.setPremium_remain(5);
			userRepository.save(standarUser);
			System.out.println("Actulizado usuario standar: " + standarUser.getEmail());
		}
		List<User> premiumUsers = userRepository.findBySubscription("premium");
		for (User premiumUser : premiumUsers) {
			premiumUser.setBuyedArticles("");
			premiumUser.setPremium_remain(10);
			userRepository.save(premiumUser);
			System.out.println("Actulizado usuario premium: " + premiumUser.getEmail());
		}
	}

	@Scheduled(cron = "*/5 * * * * *")
	public void testFunction() {
		System.out.println("Cada 5 segundos");
	}

}