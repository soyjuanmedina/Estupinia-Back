package com.estupinia.externalconnections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.estupinia.models.Article;
import com.estupinia.models.Feed;
import com.estupinia.models.Media;
import com.estupinia.tools.RSSFeedParser;
import com.estupinia.tools.SiteConstants;

public class MediaEXTERNALCONNECTION {


	public static List<Article> getAllArticles() {
		List<Media> medias = SiteConstants.MEDIAS;
		List<Article> allArticles = new ArrayList<Article>();
        for (Media media : medias) {
        	List<Article> ArticlesFromMedia = getArticlesFromMedia(media);
        	allArticles.addAll(ArticlesFromMedia);
        }
		return allArticles;
	}

	public static List<Article> getRecomendedArticles() {
		List<Media> medias = SiteConstants.MEDIAS;
		List<Article> recomendedArticles = new ArrayList<Article>();
		for (int i = 0; i < SiteConstants.RECOMENDEDARTICLESAMOUNT; ++i) {
			for (int j = 0; j < SiteConstants.MEDIAS.size(); ++j) {
				Media media = medias.get(j);
				Article recomendedArticle = getIndexArticleFromMedia(media, j);
				recomendedArticles.add(recomendedArticle);
			}
			i = i + SiteConstants.MEDIAS.size();
		}
		return recomendedArticles;
	}
	
	public static List<Article> getArticlesFromMedia(Media media) {
		List<Article> articlesFromMedia = new ArrayList<Article>();
		RSSFeedParser parser = new RSSFeedParser(media);
		Feed feed = parser.readFeed();
		articlesFromMedia = feed.getArticles();
		return articlesFromMedia;
	}
	
	public static Article getIndexArticleFromMedia(Media media, int index) {
		Article article = getArticlesFromMedia(media).get(index);
		return article;
	}

}
