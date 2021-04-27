package com.columns.security.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.columns.externalconnections.Media;
import com.columns.models.Article;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Override
	public List<Article> getRecomendedArticles() {
		return Media.getRecomendedArticles();
	}

}
