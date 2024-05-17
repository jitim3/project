package project.dto;

import java.time.Instant;

public record CreateUserDto(String username, String password, int userTypeId, Instant createdAt) {
}
