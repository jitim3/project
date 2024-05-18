package project.dao.entity;

import java.math.BigDecimal;
import java.time.Instant;

public record Room(
		Long id,
		RoomAvailabilityType roomAvailabilityType, 
		String roomType, 
		int numberOfPax,
		BigDecimal ratePerNight, 
		String description, 
		String roomImage1, 
		String roomImage2, 
		Instant createdAt,
		Instant updatedAt) {
}
