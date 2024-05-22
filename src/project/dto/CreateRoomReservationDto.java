package project.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public record CreateRoomReservationDto(
		long userId,
		long roomId,
		LocalDate startDate,
		LocalDate endDate,
		String status,
		BigDecimal amount,
		Instant createdAt) {
}
