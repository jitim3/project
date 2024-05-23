package project.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

import project.util.ReservationStatus;

public record CreateRoomReservationDto(
		long userId,
		long roomId,
		LocalDate startDate,
		LocalDate endDate,
		ReservationStatus status,
		BigDecimal amount,
		Instant createdAt) {
}
