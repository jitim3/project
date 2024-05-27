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
		Instant createdAt) implements CreateReservationDto {	
	
	public CreateCottageReservationDto(long userId, long resortId, ReservationStatus status, BigDecimal amount) {
		this(userId, resortId, null, status, amount, Instant.now());
	}
	
	public CreateCottageReservationDto createdAt(Instant createdAt) {
		return new CreateCottageReservationDto(userId, resortId, reservationDate, status, amount, createdAt);
	}
}
