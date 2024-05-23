package project.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import project.dto.CottageReservationDto;
import project.dto.CreateCottageReservationDto;
import project.dto.CreateRoomReservationDto;
import project.dto.RoomReservationDto;
import project.util.ReservationStatus;

public interface ReservationService {	
	Optional<RoomReservationDto> getRoomReservationById(long id);
	
	List<RoomReservationDto> getRoomReservations();
	
	List<RoomReservationDto> getRoomReservationsByCustomerId(long customerId);
	
	Optional<CottageReservationDto> getCottageReservationById(long id);
	
	List<CottageReservationDto> getCottageReservations();
	
	List<CottageReservationDto> getCottageReservationsByCustomerId(long customerId);

	Long createRoomReservation(CreateRoomReservationDto createRoomReservationDto);

	Long createCottageReservation(CreateCottageReservationDto createCottageReservationDto);	

	boolean updateRoomReservationStatus(long reservationRoomId, ReservationStatus status, Instant updatedAt);

	boolean updateCottageReservationStatus(long reservationCottagetId, ReservationStatus status, Instant updatedAt);
}
