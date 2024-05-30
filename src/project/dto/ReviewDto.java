package project.dto;

import java.time.Instant;

public record ReviewDto(
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
