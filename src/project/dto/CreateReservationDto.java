package project.dto;

import project.util.ReservationStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public record CreateReservationDto(
        long userId,
        Long resortId,
        Long roomId,
        LocalDate reservationDate,
        LocalDate endDate,
        ReservationStatus status,
        BigDecimal amount,
        Instant createdAt) {

    public static CreateReservationDto createCottageReservation(
            long userId,
            long resortId,
            LocalDate reservationDate,
            ReservationStatus status,
            BigDecimal amount) {
        return new CreateReservationDto(userId, resortId, null, reservationDate, null, status, amount, Instant.now());
    }

    public static CreateReservationDto createRoomReservation(long userId, long roomId,  ReservationStatus status) {
        return new CreateReservationDto(userId, null, roomId, null, null, status, null, Instant.now());
    }

    public CreateReservationDto updateRoomReservation(LocalDate reservationDate, LocalDate endDate, BigDecimal amount) {
        return new CreateReservationDto(userId, null, roomId, reservationDate, endDate, status, amount, createdAt);
    }

    public CreateReservationDto createdAt(Instant createdAt) {
        return new CreateReservationDto(userId, resortId, roomId, reservationDate, endDate, status, amount, createdAt);
    }
}