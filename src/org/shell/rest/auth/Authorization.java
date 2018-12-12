package org.shell.rest.auth;

import java.util.LinkedHashMap;

public class Authorization {
	private String role;
	private LinkedHashMap<String,String> body;
	
	public Authorization()
	{
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LinkedHashMap<String, String> getBody() {
		return body;
	}

	public void setBody(LinkedHashMap<String, String> body) {
		this.body = body;
	}
	
	
}
