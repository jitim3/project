package project.dto;

import java.time.Instant;

public record TownDto(Integer id, String name, Instant createAt, Instant updatedAt) {
}
