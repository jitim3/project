package project.service;

import java.util.List;
import java.util.Optional;

import project.dto.TownDto;

public interface TownService {
	Optional<TownDto> getTownById(int id);

	List<TownDto> getTowns();
}
