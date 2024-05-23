package project.dao.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public record CottageReservation(
		long id,
		long userId,
		long resortId,
		LocalDate reservationDate,
		String status,
		BigDecimal amount,
		Instant createdAt,
		Instant updatedAt) {
}
