package project.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

import project.util.ReservationStatus;

public record CreateCottageReservationDto(
		long userId,
		long resortId,
		LocalDate reservationDate,
		ReservationStatus status,
		BigDecimal amount,
		Instant createdAt) {
}
