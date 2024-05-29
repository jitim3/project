package project.service;

import project.dao.ReservationDao;
import project.dao.entity.CottageReservation;
import project.dao.entity.RoomReservation;
import project.dto.CottageReservationDto;
import project.dto.CreateCottageReservationDto;
import project.dto.CreateRoomReservationDto;
import project.dto.RoomReservationDto;
import project.util.ReservationStatus;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class ReservationService {
    private final ReservationDao reservationDao;

    public ReservationService() {
        this.reservationDao = new ReservationDao();
    }

    public Optional<RoomReservationDto> getRoomReservationById(long id) {
        return this.reservationDao.getRoomReservationById(id)
                .map(this::mapToRoomReservationDto);
    }

    public List<RoomReservationDto> getRoomReservations() {
        return this.reservationDao.getRoomReservations().stream()
                .map(this::mapToRoomReservationDto)
                .toList();
    }

    public List<RoomReservationDto> getRoomReservationsByCustomerId(long customerId) {
        return this.reservationDao.getRoomReservationsByCustomerId(customerId).stream()
                .map(this::mapToRoomReservationDto)
                .toList();
    }

    public Optional<CottageReservationDto> getCottageReservationById(long id) {
        return this.reservationDao.getCottageReservationById(id)
                .map(this::mapToCottageReservationDto);
    }

    public List<CottageReservationDto> getCottageReservations() {
        return this.reservationDao.getCottageReservations().stream()
                .map(this::mapToCottageReservationDto)
                .toList();
    }

    public List<CottageReservationDto> getCottageReservationsByCustomerId(long customerId) {
        return this.reservationDao.getCottageReservationsByCustomerId(customerId).stream()
                .map(this::mapToCottageReservationDto)
                .toList();
    }

    public Long createRoomReservation(CreateRoomReservationDto createRoomReservationDto) {
        return this.reservationDao.createRoomReservation(createRoomReservationDto);
    }

    public Long createCottageReservation(CreateCottageReservationDto createCottageReservationDto) {
        return this.reservationDao.createCottageReservation(createCottageReservationDto);
    }

    public boolean updateRoomReservationStatus(long reservationRoomId, ReservationStatus status, Instant updatedAt) {
        return this.reservationDao.updateRoomReservationStatus(reservationRoomId, status, updatedAt);
    }

    public boolean updateCottageReservationStatus(long reservationCottagetId, ReservationStatus status, Instant updatedAt) {
        return this.reservationDao.updateCottageReservationStatus(reservationCottagetId, status, updatedAt);
    }

    private RoomReservationDto mapToRoomReservationDto(RoomReservation roomReservation) {
        return new RoomReservationDto(
                roomReservation.id(),
                roomReservation.userId(),
                roomReservation.roomId(),
                roomReservation.startDate(),
                roomReservation.endDate(),
                roomReservation.status(),
                roomReservation.amount(),
                roomReservation.createdAt(),
                roomReservation.updatedAt()
        );
    }

    private CottageReservationDto mapToCottageReservationDto(CottageReservation cottageReservation) {
        return new CottageReservationDto(
                cottageReservation.id(),
                cottageReservation.userId(),
                cottageReservation.resortId(),
                cottageReservation.reservationDate(),
                cottageReservation.status(),
                cottageReservation.amount(),
                cottageReservation.createdAt(),
                cottageReservation.updatedAt()
        );
    }
}
