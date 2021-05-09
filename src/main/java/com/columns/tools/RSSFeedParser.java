package com.columns.tools;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

import com.columns.models.Article;
import com.columns.models.Feed;
import com.columns.models.Media;

public class RSSFeedParser {
	static final String TITLE = "title";
	static final String DESCRIPTION = "description";
	static final String CHANNEL = "channel";
	static final String LANGUAGE = "language";
	static final String COPYRIGHT = "copyright";
	static final String LINK = "link";
	static final String AUTHOR = "author";
	static final String ITEM = "item";
	static final String PUB_DATE = "pubDate";
	static final String GUID = "guid";

	final URL url;
	final String name;

	public RSSFeedParser(Media media) {
		this.name = media.getName();
		try {
			this.url = new URL(media.getUrl());
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public Feed readFeed() {
		Feed feed = null;
		try {
			boolean isFeedHeader = true;
			// Set header values intial to the empty string
			String description = "";
			String title = "";
			String link = "";
			String language = "";
			String copyright = "";
			String author = "";
			String pubdate = "";
			String fullcontent = "";

			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader
			InputStream in = read();
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			// read the XML document
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				if (event.isStartElement()) {
					String localPart = event.asStartElement().getName().getLocalPart();
					switch (localPart) {
					case ITEM:
					case "entry":
						if (isFeedHeader) {
							isFeedHeader = false;
							feed = new Feed(title, link, description, language, copyright, pubdate);
						}
						event = eventReader.nextEvent();
						break;
					case TITLE:
						if(!event.asStartElement().getName().getPrefix().equals("media")) {
							title = getCharacterData(event, eventReader);
						}
						break;
					case DESCRIPTION:
					case "summary":
						description = getCharacterData(event, eventReader);
						break;
					case LINK:
						link = getCharacterData(event, eventReader);
						break;
					case LANGUAGE:
						language = getCharacterData(event, eventReader);
						break;
					case AUTHOR:
					case "creator":
						author = getCharacterData(event, eventReader);
						break;
					case PUB_DATE:
					case "published":
						String firstFourChars = getCharacterData(event, eventReader).substring(0, 10);
//						DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm:ss Z",
//								Locale.ENGLISH);
//						OffsetDateTime odt = OffsetDateTime.parse(getCharacterData(event, eventReader), inputFormat);
//						DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("d-MM-YYYY", Locale.ENGLISH);
						pubdate = firstFourChars;
						break;
					case COPYRIGHT:
						copyright = getCharacterData(event, eventReader);
						break;
					case "encoded":
					case "content":
						if(!event.asStartElement().getName().getPrefix().equals("media")) {
							fullcontent = getCharacterData(event, eventReader);
						}
						break;
					}
				} else if (event.isEndElement()) {
					if (event.asEndElement().getName().getLocalPart() == (ITEM) ||
							event.asEndElement().getName().getLocalPart() == ("entry")) {
						Article article = new Article();
						String uniqueID = UUID.randomUUID().toString();
						article.setAuthor(author);
						article.setExcrept(description);
						article.setUrl(link);
						article.setTitle(title);
						article.setDate(pubdate);
						article.setFullcontent(fullcontent);
						article.setId(uniqueID);
						article.setMedia(this.name);
						
						feed.getArticles().add(article);
						event = eventReader.nextEvent();
						continue;
					}
				}
			}
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}
		return feed;
	}

	private String getCharacterData(XMLEvent event, XMLEventReader eventReader) throws XMLStreamException {
		String result = "";
		event = eventReader.nextEvent();
		if (event instanceof Characters) {
			result = event.asCharacters().getData();
		}
		return result;
	}

	private InputStream read() {
		try {
			return url.openStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
