package com.estupinia.security.services;

import java.util.List;

import com.estupinia.models.Article;
import com.estupinia.models.Media;

public interface ArticleService {
	List<Article> getRecomendedArticles();
	List<Article> getArticlesByMedia(Media media);
}
