package com.estupinia.websockets.models;

import com.estupinia.models.Theme;
import com.estupinia.models.User;

public class CommunicationProposal {
	
	private User from;
	
	private User to;
	
	private Theme theme;
	
	private String type;
	
	private String answer;

	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public User getTo() {
		return to;
	}

	public void setTo(User to) {
		this.to = to;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}	

}
