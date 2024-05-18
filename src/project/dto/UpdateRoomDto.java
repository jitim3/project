package project.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record UpdateRoomDto(
		long id,
		int roomAvailabilityTypeId,
		int numberOfPax,
		BigDecimal ratePerNight, 
		String description, 
		String roomImage1, 
		String roomImage2, 
		Instant updatedAt) {
}
