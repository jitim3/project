package project.service.impl;

import java.util.List;
import java.util.Optional;

import project.dao.RoomDao;
import project.dao.entity.Room;
import project.dao.impl.DefaultRoomDao;
import project.dto.CreateRoomDto;
import project.dto.RoomDto;
import project.dto.UpdateRoomDto;
import project.service.RoomService;

public class DefaultRoomService extends DtoMapper implements RoomService {
	private final RoomDao roomDao;
	
	public DefaultRoomService() {
		this.roomDao = new DefaultRoomDao();
	}

	@Override
	public Optional<RoomDto> getRoomById(long id) {
		return this.roomDao.getRoomById(id)
				.map(super::mapToRoomDto);
	}

	@Override
	public List<RoomDto> getRoomsByResortId(long resortId) {
		return this.roomDao.getRoomsByResortId(resortId).stream()
				.map(super::mapToRoomDto)
				.toList();
	}

	@Override
	public RoomDto createRoom(CreateRoomDto createRoomDto) {
		Room createdRoom = this.roomDao.createRoom(createRoomDto);
		
		return super.mapToRoomDto(createdRoom);
	}

	@Override
	public RoomDto updateRoom(UpdateRoomDto updateRoomDto) {
		Room updatedRoom = this.roomDao.updateRoom(updateRoomDto);
		
		return super.mapToRoomDto(updatedRoom);
	}

}
