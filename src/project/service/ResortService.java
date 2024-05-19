package project.service;

import java.util.List;
import java.util.Optional;

import project.dto.CreateResortDto;
import project.dto.ResortDto;
import project.dto.UpdateResortDto;

public interface ResortService {
	boolean isResortExists(String name);

	Optional<ResortDto> getResortById(int id);

	List<ResortDto> getResortsByUserId(long userId);

	List<ResortDto> getResortsByUserIdAndTownId(long userId, int townId);

	Long createResort(CreateResortDto createResortDto);
	
	boolean updateResort(UpdateResortDto updateResortDto);
}
