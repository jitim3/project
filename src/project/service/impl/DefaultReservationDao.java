package project.service.impl;

import java.time.Instant;

import project.dto.CreateCottageReservationDto;
import project.dto.CreateResortReservationDto;
import project.dto.CreateRoomReservationDto;
import project.service.ReservationService;
import project.util.ReservationStatus;

public class DefaultReservationDao implements ReservationService {
	private final ReservationService reservationService;

	public DefaultReservationDao() {
		this.reservationService = new DefaultReservationDao();
	}

	@Override
	public Long createResortReservation(CreateResortReservationDto createResortReservationDto) {
		return this.reservationService.createResortReservation(createResortReservationDto);
	}

	@Override
	public Long createRoomReservation(CreateRoomReservationDto createRoomReservationDto) {
		return this.reservationService.createRoomReservation(createRoomReservationDto);
	}

	@Override
	public Long createCottageReservation(CreateCottageReservationDto createCottageReservationDto) {
		return this.reservationService.createCottageReservation(createCottageReservationDto);
	}

	@Override
	public boolean updateResortReservationStatus(long reservationResortId, ReservationStatus status, Instant updatedAt) {
		return this.reservationService.updateResortReservationStatus(reservationResortId, status, updatedAt);
	}

	@Override
	public boolean updateRoomReservationStatus(long reservationRoomId, ReservationStatus status, Instant updatedAt) {
		return this.updateRoomReservationStatus(reservationRoomId, status, updatedAt);
	}

	@Override
	public boolean updateCottageReservationStatus(long reservationCottagetId, ReservationStatus status, Instant updatedAt) {
		return this.reservationService.updateCottageReservationStatus(reservationCottagetId, status, updatedAt);
	}

}
