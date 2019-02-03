package org.example.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	private String userName;
	private String userRole;

	public User(String userName, String userRole) {
		this.userName = userName;
		this.userRole = userRole;
	}

	public User() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
}