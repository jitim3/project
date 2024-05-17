package project.dto;

import java.time.Instant;

import project.dao.entity.UserType;

public class UserDto {
	private final Long id;
	private final String username;
	private final UserType userType;
	private final Instant createdAt;
	private final Instant updatedAt;
	
	public UserDto(Long id, String username, UserType userType, Instant createdAt, Instant updatedAt) {
		this.id = id;
		this.username = username;
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
