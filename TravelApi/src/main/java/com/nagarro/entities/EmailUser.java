package com.nagarro.entities;

import org.springframework.stereotype.Component;

@Component
public class EmailUser {

	private String emailAddress;
	private String text;
	private String attachements;

	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getAttachements() {
		return attachements;
	}
	public void setAttachements(String attachements) {
		this.attachements = attachements;
	}
	
	
}