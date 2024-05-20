package project.service.impl;

import project.dao.entity.Room;
import project.dao.entity.RoomAvailabilityType;
import project.dao.entity.Town;
import project.dto.RoomAvailabilityTypeDto;
import project.dto.RoomDto;
import project.dto.TownDto;

public class DtoMapper {	
	public TownDto mapToTownDto(Town town) {		
		return town == null ? null : new TownDto(
				town.id(),
				town.name(),
				town.createAt(),
				town.updatedAt()
			);		
	}
	
	public RoomDto mapToRoomDto(Room room) {
		return new RoomDto(
				room.id(), 
				this.mapToAvailabilityTypeDto(room.roomAvailabilityType()), 
				room.roomType(), 
				room.numberOfPax(), 
				room.ratePerNight(), 
				room.description(), 
				room.roomImage1(), 
				room.roomImage2(), 
				room.createdAt(), 
				room.updatedAt()
			);
	}
	
	public RoomAvailabilityTypeDto mapToAvailabilityTypeDto(RoomAvailabilityType roomAvailabilityType) {
		return new RoomAvailabilityTypeDto(
				roomAvailabilityType.id(), 
				roomAvailabilityType.name(), 
				roomAvailabilityType.description(), 
				roomAvailabilityType.createdAt(), 
				roomAvailabilityType.updatedAt()
			);
	}
}
