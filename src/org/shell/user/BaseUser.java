package org.shell.user;

import java.util.UUID;

public class BaseUser {
	private UUID id;
	private String email;
	private String password;
	private boolean enabled;
	public static String ROLE_PUNTER = "ROLE_PUNTER";
	public static String ROLE_ADMIN = "ROLE_ADMIN";
	private String role;
	
	public BaseUser()
	{
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "BaseUser [id=" + id + ", email=" + email + ", enabled=" + enabled + ", role=" + role + "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
