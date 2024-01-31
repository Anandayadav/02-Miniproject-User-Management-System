package com.Project.binding;

public class Quote {
	
	private String text;
	
	private String author;

	
	
	public Quote() {
		super();
	}

	public Quote(String text, String author) {
		super();
		this.text = text;
		this.author = author;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAuthor() {
		return author;
	}

	public void setauthor(String author) {
		this.author = author;
	}
	
	
	

}
