package project.dao.entity;

import java.math.BigDecimal;
import java.time.Instant;

public record Withdrawal(long id, long userId, BigDecimal amount, Instant createdAt) {
}
