package project.dao.entity;

import java.math.BigDecimal;
import java.time.Instant;

public record Payment(
		long id,
		long reservationResortId,
		long reservationRoomId,
		long reservationCottageId,
		BigDecimal reservationResortAmount,
		BigDecimal reservationRoomAmount,
		BigDecimal reservationCottageAmount,
		Instant createdAt) {
}
