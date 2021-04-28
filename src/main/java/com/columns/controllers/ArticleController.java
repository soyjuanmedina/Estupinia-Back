package com.columns.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.columns.models.Article;
import com.columns.models.User;
import com.columns.repository.UserRepository;
import com.columns.security.services.ArticleService;

@RestController
@RequestMapping("/article")
@CrossOrigin(origins = "*")
public class ArticleController {

	@Autowired
	ArticleService articleService;

	@Autowired
	UserRepository userRepository;

	@PostMapping("/recomended")
	public ResponseEntity<List<Article>> getRecomendedArticles() {
		List<Article> recomendedArticles = articleService.getRecomendedArticles();
		return ResponseEntity.ok(recomendedArticles);
	}

	@PostMapping("/confirmreadpremium")
	public ResponseEntity<Boolean> confirmReadPremium() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		User user = userRepository.findByUsername(currentPrincipalName).get();
		user.setPremium_remain(user.getPremium_remain() - 1);
		userRepository.save(user);
		Boolean response = true;
		return ResponseEntity.ok(response);
	}

}
