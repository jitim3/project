package project.dao.entity;

import java.time.Instant;

public class User {
	private final Long id;
	private final String username;
	private final String password;
	private final UserType userType;
	private final Instant createdAt;
	private final Instant updatedAt;
	
	public User(Long id, String username, String password, UserType userType, Instant createdAt, Instant updatedAt) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public UserType getUserType() {
		return userType;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}
}
