package project.dao.entity;

import java.time.Instant;

public record Review(
		Long id,
		Long userId,
		String firstName,
		String lastName,
		Long resortId,
		Integer rate,
		String comment,
		Instant createdAt,
		Instant updatedAt) {
}
