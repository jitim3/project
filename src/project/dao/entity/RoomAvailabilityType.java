package project.dao.entity;

import java.time.Instant;

public record RoomAvailabilityType(int id, String name, Instant createdAt, Instant updatedAt) {
}
