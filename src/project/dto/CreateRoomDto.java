package project.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record CreateRoomDto(
		int resortId,
		int roomAvailabilityTypeId, 
		String roomType, 
		int numberOfPax,
		BigDecimal ratePerNight, 
		String description, 
		String roomImage1, 
		String roomImage2, 
		Instant createdAt) {
}
