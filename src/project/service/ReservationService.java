package project.service;

import project.dao.ReservationDao;
import project.dao.entity.CommissionRate;
import project.dao.entity.Reservation;
import project.dto.CreateReservationDto;
import project.dto.ReservationDto;
import project.util.ReservationStatus;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class ReservationService {
    private final ReservationDao reservationDao;

    public ReservationService() {
        this.reservationDao = new ReservationDao();
    }

    public Optional<ReservationDto> getReservationById(long id) {
        return this.reservationDao.getReservationById(id)
                .map(this::mapToReservationDto);
    }

    public List<ReservationDto> getReservations() {
        return this.reservationDao.getReservations().stream()
                .map(this::mapToReservationDto)
                .toList();
    }

    public List<ReservationDto> getReservationsByCustomerId(long customerId) {
        return this.reservationDao.getReservationsByCustomerId(customerId).stream()
                .map(this::mapToReservationDto)
                .toList();
    }

    public List<ReservationDto> getReservationsByUserId(long userId) {
        return this.reservationDao.getReservationsByUserId(userId).stream()
                .map(this::mapToReservationDto)
                .toList();
    }

    public List<ReservationDto> getReservationsByResortId(long resortId) {
        return this.reservationDao.getReservationsByResortId(resortId).stream()
                .map(this::mapToReservationDto)
                .toList();
    }

    public Long createReservation(CreateReservationDto createReservationDto) {
        return this.reservationDao.createReservation(createReservationDto);
    }

    public boolean updateReservationStatus(long reservationId, ReservationStatus status, Instant updatedAt) {
        return this.reservationDao.updateReservationStatus(reservationId, status, updatedAt);
    }

    public CommissionRate getCommissionRate() {
        return this.reservationDao.getCommissionRate();
    }

    public CommissionRate getCommissionRateById(int id) {
        return this.reservationDao.getCommissionRateById(id);
    }

    private ReservationDto mapToReservationDto(Reservation reservation) {
        return new ReservationDto(
                reservation.id(),
                reservation.userId(),
                reservation.resortId(),
                reservation.resortName(),
                reservation.roomResortId(),
                reservation.roomResortName(),
                reservation.roomId(),
                reservation.roomType(),
                reservation.reservationDate(),
                reservation.endDate(),
                reservation.status(),
                reservation.amount(),
                reservation.commissionRateId(),
                reservation.createdAt(),
                reservation.updatedAt()
        );
    }
}
