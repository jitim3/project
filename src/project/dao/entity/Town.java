package project.dao.entity;

import java.time.Instant;

public record Town(Integer id, String name, Instant createAt, Instant updatedAt) {
}
