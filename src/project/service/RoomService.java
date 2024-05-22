package project.service;

import java.util.List;
import java.util.Optional;

import project.dto.CreateRoomDto;
import project.dto.RoomDto;
import project.dto.UpdateRoomDto;

public interface RoomService {
	Optional<RoomDto> getRoomById(long id);

	List<RoomDto> getRoomsByResortId(long resortId);
	
	RoomDto createRoom(CreateRoomDto createRoomDto);

	RoomDto updateRoom(UpdateRoomDto updateRoomDto);
}
