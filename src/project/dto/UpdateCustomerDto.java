package project.dto;

import java.time.Instant;

public record UpdateCustomerDto(
		Long id,
		String firstName,
		String lastName,
		String contactNumber,
		String emailAddress,
		Instant updatedAt) {
}
