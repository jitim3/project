package project.service.impl;

import project.dao.entity.Town;
import project.dto.TownDto;

public class TownMapper {	
	public TownDto mapToTownDto(Town town) {		
		return town == null ? null : new TownDto(
				town.id(),
				town.name(),
				town.createAt(),
				town.updatedAt()
			);		
	}
}
