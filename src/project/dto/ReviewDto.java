package project.dto;

import java.time.Instant;

public record ReviewDto(
		Long id,
		Long userId,
		Long resortId,
		Integer rate,
		String comment,
		Instant createdAt,
		Instant updatedAt) {
}
