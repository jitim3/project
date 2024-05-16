package project.dto;

import java.time.Instant;

public class UserDto {
	private final Long id;
	private final String username;
	private final int userTypeId;
	private final Instant createdAt;
	private final Instant updatedAt;
	
	public UserDto(Long id, String username, int userTypeId, Instant createdAt, Instant updatedAt) {
		this.id = id;
		this.username = username;
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
