package project.dao.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record Resort(
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
		List<Room> rooms,
		Long userId, 
		int townId, 
		Instant createdAt, 
		Instant updatedAt) {
}
