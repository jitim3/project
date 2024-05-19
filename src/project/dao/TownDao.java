package project.dao;

import java.util.List;
import java.util.Optional;

import project.dao.entity.Town;

public interface TownDao {
	Optional<Town> getTownById(int id);

	List<Town> getTowns();
}
