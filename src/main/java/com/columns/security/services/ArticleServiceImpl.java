package com.columns.security.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.columns.externalconnections.MediaEXTERNALCONNECTION;
import com.columns.models.Article;
import com.columns.models.Media;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Override
	public List<Article> getRecomendedArticles() {
		return MediaEXTERNALCONNECTION.getRecomendedArticles();
	}
	
	@Override
	public List<Article> getArticlesByMedia(Media media) {
		return MediaEXTERNALCONNECTION.getArticlesFromMedia(media);
	}

}
