package org.example.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

    private Long id;
	private String userName;
	private String userRole;

	public User() { }

	public User(String userName, String userRole) {
		this.userName = userName;
		this.userRole = userRole;
	}

	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

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