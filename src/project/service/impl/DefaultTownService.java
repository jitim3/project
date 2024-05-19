package project.service.impl;

import java.util.List;
import java.util.Optional;

import project.dao.TownDao;
import project.dao.impl.DefaultTownDao;
import project.dto.TownDto;
import project.service.TownService;

public class DefaultTownService extends TownMapper implements TownService {
	private final TownDao townDao;
	
	public DefaultTownService() {
		this.townDao = new DefaultTownDao(); 
	}

	@Override
	public Optional<TownDto> getTownById(int id) {
		return this.townDao.getTownById(id)
				.map(super::mapToTownDto);
	}

	@Override
	public List<TownDto> getTowns() {
		return this.townDao.getTowns().stream()
				.map(super::mapToTownDto)
				.toList();
	}

}
