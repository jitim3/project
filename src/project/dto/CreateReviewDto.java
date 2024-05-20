package project.dto;

import java.time.Instant;

public record CreateReviewDto(Long userId, Long resortId, String comment, Instant createdAt) {
}
