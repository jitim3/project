package project.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record UpdateResortDto(
		long id, 
		String description, 
		String location, 
		String howToGetThere,
		BigDecimal resortFee, 
		BigDecimal cottageFee, 
		BigDecimal poolFee, 
		String resortImage, 
		String poolImage,
		String cottageImage,
		Instant updatedAt) {
}
