package com.columns.security.services;

import java.util.List;

import com.columns.models.Article;

public interface ArticleService {
	List<Article> getRecomendedArticles();
}
