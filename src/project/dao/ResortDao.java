package project.dao;

import java.util.List;
import java.util.Optional;

import project.dao.entity.Resort;
import project.dto.CreateResortDto;
import project.dto.UpdateResortDto;

public interface ResortDao {
	boolean isResortExists(String name);

	Optional<Resort> getResortById(int id);

	List<Resort> getResortsByUserIdAndTownId(long userId, int townId);

	Long createResort(CreateResortDto createResortDto);
	
	boolean updateResort(UpdateResortDto updateResortDto);
}
