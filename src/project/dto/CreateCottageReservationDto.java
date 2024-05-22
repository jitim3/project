package project.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public record CreateCottageReservationDto(
		long userId,
		long resortId,
		LocalDate startDate,
		LocalDate endDate,
		String status,
		BigDecimal amount,
		Instant createdAt) {
}