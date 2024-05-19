package project.dto;

import java.time.Instant;

public record RoomAvailabilityTypeDto(int id, String name, String description, Instant createdAt, Instant updatedAt) {
}
