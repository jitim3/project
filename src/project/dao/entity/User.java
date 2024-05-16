package project.dao.entity;

import java.time.Instant;

public class User {
	private final Long id;
	private final String username;
	private final String password;
	private final int userTypeId;
	private final Instant createdAt;
	private final Instant updatedAt;
	
	public User(Long id, String username, String password, int userTypeId, Instant createdAt, Instant updatedAt) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.userTypeId = userTypeId;
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

	public int getUserTypeId() {
		return userTypeId;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}
}
