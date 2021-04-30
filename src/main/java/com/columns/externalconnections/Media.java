package com.columns.externalconnections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.columns.models.Article;
import com.columns.models.Feed;
import com.columns.tools.RSSFeedParser;

public class Media {

	private List<String> medias = Arrays.asList("https://www.vozpopuli.com/opinion/feed",
			"https://rss.blogs.elconfidencial.com/",
			"https://rss.blogs.elconfidencial.com/espana/al-grano/");

	public List<Article> getAllArticles() {
		
		List<Article> allArticles = new ArrayList<Article>();
        for (String media : this.medias) {
            System.out.println(media);

        }
		return allArticles;
	}

	public static List<Article> getRecomendedArticles() {
		List<Article> recomendedArticles = new ArrayList<Article>();
		RSSFeedParser parser = new RSSFeedParser("https://rss.blogs.elconfidencial.com/");
		Feed feed = parser.readFeed();
		recomendedArticles = feed.getArticles();
		return recomendedArticles;
	}
	
	public static List<Article> getArticlesFromMedia(String media) {
		List<Article> articlesFromMedia = new ArrayList<Article>();
		RSSFeedParser parser = new RSSFeedParser(media);
		Feed feed = parser.readFeed();
		articlesFromMedia = feed.getArticles();
		return articlesFromMedia;
	}

}
