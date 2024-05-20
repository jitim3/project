package project.dto;

import java.time.Instant;

public record RoomAvailabilityTypeDto(int id, String name, Instant createdAt, Instant updatedAt) {
}
