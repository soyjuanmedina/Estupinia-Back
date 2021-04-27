package com.columns.models;

public class Article {

	private String id;
	private String title;
	private String media;
	private String writer;
	private String date;
	private String img;
	private String fullcontent;
	private String epigraph;
	private String excrept;
	private String url;
	private Boolean premium;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getFullcontent() {
		return fullcontent;
	}

	public void setFullcontent(String fullcontent) {
		this.fullcontent = fullcontent;
	}

	public String getEpigraph() {
		return epigraph;
	}

	public void setEpigraph(String epigraph) {
		this.epigraph = epigraph;
	}

	public String getExcrept() {
		return excrept;
	}

	public void setExcrept(String excrept) {
		this.excrept = excrept;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getPremium() {
		return premium;
	}

	public void setPremium(Boolean premium) {
		this.premium = premium;
	}

}
