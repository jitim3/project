package project.ui;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public record Reservation(
        long id,
        String type,
        LocalDate reservationDate,
        LocalDate endDate,
        BigDecimal amount,
        String status,
        Instant createdAt) {
}
