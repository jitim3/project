package project.dao;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import project.dao.entity.CottageReservation;
import project.dao.entity.RoomReservation;
import project.dto.CreateCottageReservationDto;
import project.dto.CreateRoomReservationDto;
import project.util.ReservationStatus;

public interface ReservationDao {
	
	Optional<RoomReservation> getRoomReservationById(long id);
	
	List<RoomReservation> getRoomReservations();
	
	List<RoomReservation> getRoomReservationsByCustomerId(long customerId);
	
	Optional<CottageReservation> getCottageReservationById(long id);
	
	List<CottageReservation> getCottageReservations();
	
	List<CottageReservation> getCottageReservationsByCustomerId(long customerId);

	Long createRoomReservation(CreateRoomReservationDto createRoomReservationDto);

	Long createCottageReservation(CreateCottageReservationDto createCottageReservationDto);

	boolean updateRoomReservationStatus(long reservationRoomId, ReservationStatus status, Instant updatedAt);

	boolean updateCottageReservationStatus(long reservationCottagetId, ReservationStatus status, Instant updatedAt);
}
