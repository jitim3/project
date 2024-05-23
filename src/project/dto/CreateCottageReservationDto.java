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
	
	public CreateCottageReservationDto(long userId, long resortId, ReservationStatus status) {
		this(userId, resortId, null, status, new BigDecimal(0), Instant.now());
	}
	
	public CreateCottageReservationDto amount(BigDecimal amount) {
		return new CreateCottageReservationDto(userId, resortId, reservationDate, status, amount, createdAt);
	}
	
	public CreateCottageReservationDto createdAt(Instant createdAt) {
		return new CreateCottageReservationDto(userId, resortId, reservationDate, status, amount, createdAt);
	}
}
