package project.dao.entity;

import java.time.Instant;

public class Resort {
	private final Long id;
	private final String name;
	private final int townId;
	private final long userId;
	private final Instant createdAt;
	private final Instant updatedAt;
	
	public Resort(Long id, String name, int townId, long userId, Instant createdAt, Instant updatedAt) {
		this.id = id;
		this.name = name;
		this.townId = townId;
		this.userId = userId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getTownId() {
		return townId;
	}

	public long getUserId() {
		return userId;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}
}
