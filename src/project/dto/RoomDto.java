package project.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record RoomDto(
		Long id,
		RoomAvailabilityTypeDto roomAvailabilityTypeDto, 
		String roomType, 
		int numberOfPax,
		BigDecimal ratePerNight, 
		String description, 
		String roomImage1, 
		String roomImage2, 
		Instant createdAt,
		Instant updatedAt) {
}
