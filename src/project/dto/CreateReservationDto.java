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
        int commissionRateId,
        Instant createdAt) {

    public static CreateReservationDto createCottageReservation(
            long userId,
            long resortId,
            LocalDate reservationDate,
            ReservationStatus status,
            BigDecimal amount,
            int commissionRateId) {
        return new CreateReservationDto(userId, resortId, null, reservationDate, null, status, amount, commissionRateId, Instant.now());
    }

    public static CreateReservationDto createRoomReservation(long userId, long roomId,  ReservationStatus status, int commissionRateId) {
        return new CreateReservationDto(userId, null, roomId, null, null, status, null, commissionRateId, Instant.now());
    }

    public CreateReservationDto updateRoomReservation(LocalDate reservationDate, LocalDate endDate, BigDecimal amount) {
        return new CreateReservationDto(userId, null, roomId, reservationDate, endDate, status, amount, commissionRateId, createdAt);
    }

    public CreateReservationDto createdAt(Instant createdAt) {
        return new CreateReservationDto(userId, resortId, roomId, reservationDate, endDate, status, amount, commissionRateId, createdAt);
    }
}