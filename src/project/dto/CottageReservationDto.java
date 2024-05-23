package project.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public record CottageReservationDto(
		long id,
		long userId,
		long resortId,
		LocalDate reservationDate,
		String status,
		BigDecimal amount,
		Instant createdAt,
		Instant updatedAt) {
}
