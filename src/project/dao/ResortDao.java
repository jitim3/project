package project.dao;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import project.dao.entity.Resort;
import project.dto.CreateResortDto;
import project.dto.UpdateResortDto;

public interface ResortDao {
	boolean isResortExists(String name);

	Optional<Resort> getResortById(long id);

	Optional<Resort> getResortByUserId(long id);

	Optional<Resort> getResortByUserIdAndTownId(long userId, int townId);

	List<Resort> getResortsByTownId(int townId, boolean approved);

	Long createResort(CreateResortDto createResortDto);
	
	boolean updateResort(UpdateResortDto updateResortDto);
	
	boolean updatePermitImage(long resortId, String permitImage, Instant updatedAt);
}
