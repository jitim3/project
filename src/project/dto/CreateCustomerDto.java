package project.dto;

import java.time.Instant;

public record CreateCustomerDto(
		Long id,
		String firstName,
		String lastName,
		String contactNumber,
		String emailAddress,
		Instant createdAt) {
}
