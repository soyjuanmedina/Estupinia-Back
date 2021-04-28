package com.columns.externalconnections;

import java.util.ArrayList;
import java.util.List;

import com.columns.models.Article;
import com.columns.models.Feed;
import com.columns.tools.RSSFeedParser;

public class Media {

	public static List<Article> getAllArticles() {

		RSSFeedParser parser = new RSSFeedParser("https://www.vozpopuli.com/opinion/feed");
		Feed feed = parser.readFeed();
		System.out.println(feed);
//        for (Article message : article.getMessages()) {
//            System.out.println("asdf");
//
//        }

		List<Article> recomendedArticles = new ArrayList<Article>();
		Article recomendedArticle = new Article();

		recomendedArticle.setTitle("Grettings desde el back");
		recomendedArticles.add(recomendedArticle);
		return recomendedArticles;
	}

	public static List<Article> getRecomendedArticles() {
		List<Article> recomendedArticles = new ArrayList<Article>();
		RSSFeedParser parser = new RSSFeedParser("https://www.vozpopuli.com/opinion/feed");
		// RSSFeedParser parser = new
		// RSSFeedParser("https://rss.blogs.elconfidencial.com/");
		Feed feed = parser.readFeed();
		// System.out.println(feed);
		recomendedArticles = feed.getArticles();
//        for (Article message : article.getMessages()) {
//            System.out.println("asdf");
//
//        }

//		List<Article> recomendedArticles = new ArrayList<Article>();
//		Article recomendedArticle = new Article();
//
//		recomendedArticle.setTitle("Grettings desde el back");
//		recomendedArticles.add(recomendedArticle);
		return recomendedArticles;
	}

}
