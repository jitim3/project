package project.dao.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public record Reservation(
        long id,
        long userId,
        Long resortId,
        String resortName,
        Long roomResortId,
        String roomResortName,
        Long roomId,
        String roomType,
        LocalDate reservationDate,
        LocalDate endDate,
        String status,
        BigDecimal amount,
        int commissionRateId,
        Instant createdAt,
        Instant updatedAt) {
}
