package project.dao;

import java.time.Instant;

import project.dto.CreateCottageReservationDto;
import project.dto.CreateResortReservationDto;
import project.dto.CreateRoomReservationDto;
import project.util.ReservationStatus;

public interface ReservationDao {
	Long createResortReservation(CreateResortReservationDto createResortReservationDto);

	Long createRoomReservation(CreateRoomReservationDto createRoomReservationDto);

	Long createCottageReservation(CreateCottageReservationDto createCottageReservationDto);

	boolean updateResortReservationStatus(long reservationResortId, ReservationStatus status, Instant updatedAt);

	boolean updateRoomReservationStatus(long reservationRoomId, ReservationStatus status, Instant updatedAt);

	boolean updateCottageReservationStatus(long reservationCottagetId, ReservationStatus status, Instant updatedAt);
}
