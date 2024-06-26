package project.dto;

import java.time.Instant;

public record CreateReviewDto(Long userId, Long resortId, Integer rate, String comment, Instant createdAt) {
}
