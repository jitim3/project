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
		Instant createdAt) implements CreateReservationDto {
	
	public CreateRoomReservationDto(long userId, long roomId,  ReservationStatus status) {
		this(userId, roomId, null, null, status, new BigDecimal(0), Instant.now());
	}
	
	public CreateRoomReservationDto startDate(LocalDate startDate) {
		return new CreateRoomReservationDto(userId, roomId, startDate, endDate, status, amount, createdAt);
	}
	
	public CreateRoomReservationDto endDate(LocalDate endDate) {
		return new CreateRoomReservationDto(userId, roomId, startDate, endDate, status, amount, createdAt);
	}
	
	public CreateRoomReservationDto amount(BigDecimal amount) {
		return new CreateRoomReservationDto(userId, roomId, startDate, endDate, status, amount, createdAt);
	}
	
	public CreateRoomReservationDto createdAt(Instant createdAt) {
		return new CreateRoomReservationDto(userId, roomId, startDate, endDate, status, amount, createdAt);
	}
	
}
