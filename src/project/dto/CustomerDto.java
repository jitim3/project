package project.dto;

import java.time.Instant;

public record CustomerDto(
		Long id, 
		String username,
		String firstName, 
		String lastName, 
		String contactNumber, 
		String emailAddress,
		Instant createdAt,
		Instant updatedAt) {
}
