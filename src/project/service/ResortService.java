package project.service;

import java.util.List;
import java.util.Optional;

import project.dto.CreateResortDto;
import project.dto.ResortDto;
import project.dto.UpdateResortDto;

public interface ResortService {
	boolean isResortExists(String name);

	Optional<ResortDto> getResortById(long id);
	
	Optional<ResortDto> getResortByUserId(long userId);

	Optional<ResortDto> getResortByUserIdAndTownId(long userId, int townId);

	List<ResortDto> getResortsByTownId(int townId, boolean approved);

	Long createResort(CreateResortDto createResortDto);
	
	boolean updateResort(UpdateResortDto updateResortDto);
}
