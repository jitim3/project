package project.dao;

import java.util.Optional;

import project.dao.entity.Resort;

public interface ResortDao {
	boolean isResortExists(String name);

	Optional<Resort> getResortById(int id);

	Optional<Resort> getResortByName(String name);

	Optional<Resort> getResortByUserId(long userId);

	boolean createResort(Resort resort);
}
