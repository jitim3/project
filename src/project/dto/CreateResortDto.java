package project.dto;

import java.time.Instant;

public record CreateResortDto(String name, long userId, int townId, Instant createdAt) {
}
