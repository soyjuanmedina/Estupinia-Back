package com.estupinia.security.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.estupinia.externalconnections.MediaEXTERNALCONNECTION;
import com.estupinia.models.Article;
import com.estupinia.models.Media;

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
