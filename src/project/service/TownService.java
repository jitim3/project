package project.service;

import project.dao.TownDao;
import project.dto.TownDto;

import java.util.List;
import java.util.Optional;

public class TownService extends DtoMapper {
	private final TownDao townDao;
	
	public TownService() {
		this.townDao = new TownDao();
	}

	public Optional<TownDto> getTownById(int id) {
		return this.townDao.getTownById(id)
				.map(super::mapToTownDto);
	}

	public List<TownDto> getTowns() {
		return this.townDao.getTowns().stream()
				.map(super::mapToTownDto)
				.toList();
	}

}
