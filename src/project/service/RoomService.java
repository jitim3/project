package project.service;

import project.dao.RoomDao;
import project.dao.entity.Room;
import project.dto.CreateRoomDto;
import project.dto.RoomDto;
import project.dto.UpdateRoomDto;

import java.util.List;
import java.util.Optional;

public class RoomService extends DtoMapper {
    private final RoomDao roomDao;

    public RoomService() {
        this.roomDao = new RoomDao();
    }

    public Optional<RoomDto> getRoomById(long id) {
        return this.roomDao.getRoomById(id)
                .map(super::mapToRoomDto);
    }

    public List<RoomDto> getRoomsByResortId(long resortId) {
        return this.roomDao.getRoomsByResortId(resortId).stream()
                .map(super::mapToRoomDto)
                .toList();
    }

    public RoomDto createRoom(CreateRoomDto createRoomDto) {
        Room createdRoom = this.roomDao.createRoom(createRoomDto);

        return super.mapToRoomDto(createdRoom);
    }

    public RoomDto updateRoom(UpdateRoomDto updateRoomDto) {
        Room updatedRoom = this.roomDao.updateRoom(updateRoomDto);

        return super.mapToRoomDto(updatedRoom);
    }

}
