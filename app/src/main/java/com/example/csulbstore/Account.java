package com.example.csulbstore;

public class Account {
	private String password, name, lastName, email;

	public Account(String na, String ln, String em, String pass)
	{
		email = em;
		name = na;
		lastName = ln;
		password = pass;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	} 
}
