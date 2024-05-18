package project.dao.entity;

import java.time.Instant;

public record UserType(int id, String name, String description, Instant createdAt, Instant updatedAt) {
}
