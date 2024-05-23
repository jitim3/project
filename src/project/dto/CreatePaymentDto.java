package project.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record CreatePaymentDto(
		long reservationResortId,
		long reservationRoomId,
		long reservationCottageId,
		BigDecimal reservationResortAmount,
		BigDecimal reservationRoomAmount,
		BigDecimal reservationCottageAmount,
		Instant createdAt) {
}
