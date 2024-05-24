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
		String permitImage,
		Boolean approved,
		String approvedBy,
		Instant approvedAt,		
		Instant createdAt, 
		Instant updatedAt) {
	
	public ResortDto() {
		this(0, "", "", "", "", new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), "", "", "", List.of(), null, null, false, null, null, null, null);
	}	
}
