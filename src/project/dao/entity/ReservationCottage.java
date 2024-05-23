package project.dao.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public record ReservationCottage(
		long id,
		long userId,
		long resortId,
		LocalDate startDate,
		LocalDate endDate,
		String status,
		BigDecimal amount,
		Instant createdAt,
		Instant updatedAt) {
}
