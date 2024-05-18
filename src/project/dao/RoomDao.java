package project.dao;

import java.util.List;
import java.util.Optional;

import project.dao.entity.Room;
import project.dto.CreateRoomDto;
import project.dto.UpdateRoomDto;

public interface RoomDao {
	Optional<Room> getRoomById(long id);

	List<Room> getRoomByResortId(long resortId);
	
	Room createRoom(CreateRoomDto createRoomDto);

	Room updateRoom(UpdateRoomDto updateRoomDto);
}
