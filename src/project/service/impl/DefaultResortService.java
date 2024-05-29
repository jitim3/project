package project.service.impl;

import project.dao.ResortDao;
import project.dao.RoomDao;
import project.dao.entity.Resort;
import project.dao.entity.Room;
import project.dao.impl.DefaultResortDao;
import project.dao.impl.DefaultRoomDao;
import project.dto.CreateResortDto;
import project.dto.ResortDto;
import project.dto.UpdateResortDto;
import project.service.ResortService;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class DefaultResortService extends DtoMapper implements ResortService {
    private final ResortDao resortDao;
    private final RoomDao roomDao;

    public DefaultResortService() {
        this.resortDao = new DefaultResortDao();
        this.roomDao = new DefaultRoomDao();
    }

    @Override
    public boolean isResortExists(String name) {
        return this.resortDao.isResortExists(name);
    }

    @Override
    public Optional<ResortDto> getResortById(long id) {
        return this.resortDao.getResortById(id)
                .map(resort -> {
                    List<Room> rooms = this.roomDao.getRoomsByResortId(id);
                    return this.mapToResortDto(resort, rooms);
                });
    }

    @Override
    public Optional<ResortDto> getResortByUserId(long userId) {
        return this.resortDao.getResortByUserId(userId)
                .map(resort -> {
                    List<Room> rooms = this.roomDao.getRoomsByResortId(resort.id());
                    return this.mapToResortDto(resort, rooms);
                });
    }

    @Override
    public Optional<ResortDto> getResortByUserIdAndTownId(long userId, int townId) {
        return this.resortDao.getResortByUserIdAndTownId(userId, townId)
                .map(resort -> {
                    List<Room> rooms = this.roomDao.getRoomsByResortId(resort.id());
                    return this.mapToResortDto(resort, rooms);
                });
    }

    @Override
    public List<ResortDto> getResortsByTownId(int townId) {
        List<Resort> resorts = this.resortDao.getResortsByTownId(townId);

        return resorts.stream()
                .map(resort -> {
                    List<Room> rooms = this.roomDao.getRoomsByResortId(resort.id());
                    return this.mapToResortDto(resort, rooms);
                })
                .toList();
    }

    @Override
    public Long createResort(CreateResortDto createResortDto) {
        return this.resortDao.createResort(createResortDto);
    }

    @Override
    public boolean updateResort(UpdateResortDto updateResortDto) {
        return this.resortDao.updateResort(updateResortDto);
    }

    @Override
    public boolean updatePermitImage(long resortId, String permitImage, Instant updatedAt) {
        return this.resortDao.updatePermitImage(resortId, permitImage, updatedAt);
    }

    private ResortDto mapToResortDto(Resort resort, List<Room> rooms) {
        return new ResortDto(
                resort.id(),
                resort.name(),
                resort.description(),
                resort.location(),
                resort.howToGetThere(),
                resort.resortFee(),
                resort.cottageFee(),
                resort.poolFee(),
                resort.resortImage(),
                resort.poolImage(),
                resort.cottageImage(),
                rooms.stream()
                        .map(super::mapToRoomDto)
                        .toList(),
                resort.permitImage(),
                resort.createdAt(),
                resort.updatedAt()
        );
    }
}
