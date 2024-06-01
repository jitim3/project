package project.dao.entity;

import java.math.BigDecimal;
import java.time.Instant;

public record Resort(
		Long id, 
		String name, 
		String description, 
		String location,
		int townId,
		String howToGetThere,
		BigDecimal resortFee, 
		BigDecimal cottageFee, 
		BigDecimal poolFee, 
		String resortImage, 
		String poolImage,
		String cottageImage,
		Long userId,
		String permitImage,
		Instant createdAt, 
		Instant updatedAt) {
}
