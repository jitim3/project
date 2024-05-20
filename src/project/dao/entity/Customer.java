package project.dao.entity;

import java.time.Instant;

public record Customer(
		Long id, 
		String username, 
		String firstName, 
		String lastName, 
		String contactNumber, 
		String emailAddress,
		Instant createdAt,
		Instant updatedAt) {
}
