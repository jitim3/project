package project.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record ResortDto(
		long id, 
		String name, 
		String description, 
		String location, 
		String howToGetThere,
		BigDecimal resortFee, 
		BigDecimal cottageFee, 
		BigDecimal poolFee, 
		String resortImage, 
		String poolImage,
		String cottageImage, 
		List<RoomDto> roomDtos,
		TownDto townDto, 
		Instant createdAt, 
		Instant updatedAt) {
}
