package com.estupinia.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.estupinia.models.Media;

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

	public class MAIL {
		public static final String BASE_URL = "https://soyjuanmedina.github.io/Estupinia-Front/";
		public static final String REGISTRATION_URL = "https://soyjuanmedina.github.io/Estupinia-Front/#/confirmemail/";
	}

}