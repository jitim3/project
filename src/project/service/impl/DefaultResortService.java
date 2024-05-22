package project.service.impl;

import java.util.List;
import java.util.Optional;

import project.dao.ResortDao;
import project.dao.RoomDao;
import project.dao.TownDao;
import project.dao.entity.Resort;
import project.dao.entity.Room;
import project.dao.entity.Town;
import project.dao.impl.DefaultResortDao;
import project.dao.impl.DefaultRoomDao;
import project.dao.impl.DefaultTownDao;
import project.dto.CreateResortDto;
import project.dto.ResortDto;
import project.dto.UpdateResortDto;
import project.service.ResortService;

public class DefaultResortService extends DtoMapper implements ResortService {
	private final ResortDao resortDao;
	private final RoomDao roomDao;
	private final TownDao townDao;
	
	public DefaultResortService() {
		this.resortDao = new DefaultResortDao();
		this.roomDao = new DefaultRoomDao();
		this.townDao = new DefaultTownDao();
	}

	@Override
	public boolean isResortExists(String name) {
		return this.resortDao.isResortExists(name);
	}

	@Override
	public Optional<ResortDto> getResortById(long id) {
		 return this.resortDao.getResortById(id)
				 .map(resort -> {
					 int townId = resort.townId();
					 Optional<Town> townOptional = this.townDao.getTownById(townId);
					 List<Room> rooms = this.roomDao.getRoomsByResortId(id);
					 return this.mapToResortDto(resort, rooms, townOptional.orElse(null));
				 });
	}

	@Override
	public Optional<ResortDto> getResortByUserId(long userId) {
		return this.resortDao.getResortByUserId(userId)
				.map(resort -> {
					 int townId = resort.townId();
					 Optional<Town> townOptional = this.townDao.getTownById(townId);
					 List<Room> rooms = this.roomDao.getRoomsByResortId(resort.id());
					 return this.mapToResortDto(resort, rooms, townOptional.orElse(null));
				 });
	}

	@Override
	public Optional<ResortDto> getResortByUserIdAndTownId(long userId, int townId) {
		return this.resortDao.getResortByUserIdAndTownId(userId, townId)
				.map(resort -> {
					 Optional<Town> townOptional = this.townDao.getTownById(townId);
					 List<Room> rooms = this.roomDao.getRoomsByResortId(resort.id());
					 return this.mapToResortDto(resort, rooms, townOptional.orElse(null));
				 });
	}

	@Override
	public List<ResortDto> getResortsByTownId(int townId, boolean approved) {
		List<Resort> resorts = this.resortDao.getResortsByTownId(townId, approved);
		
		return resorts.stream()
				.map(resort -> {
					 Optional<Town> townOptional = this.townDao.getTownById(resort.townId());
					 List<Room> rooms = this.roomDao.getRoomsByResortId(resort.id());
					 return this.mapToResortDto(resort, rooms, townOptional.orElse(null));
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
	
	private ResortDto mapToResortDto(Resort resort, List<Room> rooms, Town town) {
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
				super.mapToTownDto(town),
				resort.approved(),
				resort.approvedBy(),
				resort.approvedAt(), 
				resort.createdAt(), 
				resort.updatedAt()
			);
	}
}
