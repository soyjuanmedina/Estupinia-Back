package com.columns.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.columns.models.Media;

public class SiteConstants {
	
	public static List<Media> MEDIAS = new ArrayList<Media>(
            Arrays.asList(
           		//new Media("El Confidencial", "https://rss.blogs.elconfidencial.com/"),
           		//new Media("Al Grano", "https://rss.blogs.elconfidencial.com/espana/al-grano/"),
             //new Media("El Mundo", "https://e00-elmundo.uecdn.es/elmundo/rss/opinion.xml"),	
           		new Media("El País", "http://ep00.epimg.net/rss/elpais/opinion.xml")
            		));
	
	public static int RECOMENDEDARTICLESAMOUNT = 7;

	public class JSON {
		public static final String RESULT_ERROR = "2";
		public static final String RESULT_OK = "0";
		public static final String RESULT_WARNING = "1";
	}
	
	
}