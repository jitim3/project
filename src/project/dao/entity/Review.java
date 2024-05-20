package project.dao.entity;

import java.time.Instant;

public record Review(
		Long id,
		Long userId,
		Long resortId,
		String comment,
		Instant createdAt,
		Instant updatedAt) {
}
