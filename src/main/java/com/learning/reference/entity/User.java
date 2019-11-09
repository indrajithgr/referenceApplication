package com.learning.reference.entity;

public class User {
	private String login;
	private String blog;
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getBlog() {
		return blog;
	}
	public void setBlog(String blog) {
		this.blog = blog;
	}
	
	@Override
	public String toString() {
		return "User [login=" + login + ", blog=" + blog + "]";
	}
}
