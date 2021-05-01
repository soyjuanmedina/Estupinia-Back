package com.columns.security.services;

import java.util.List;

import com.columns.models.Article;
import com.columns.models.Media;

public interface ArticleService {
	List<Article> getRecomendedArticles();
	List<Article> getArticlesByMedia(Media media);
}
