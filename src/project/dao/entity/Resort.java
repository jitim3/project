package project.dao.entity;

import java.math.BigDecimal;
import java.time.Instant;

public record Resort(
		Long id, 
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
		Long userId, 
		int townId, 
		Boolean approved,
		String approvedBy,
		Instant approvedAt, 
		Instant createdAt, 
		Instant updatedAt) {
}
