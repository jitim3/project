package project.dao.entity;

import java.math.BigDecimal;
import java.time.Instant;

public record CommissionRate(int id, BigDecimal rate, Instant createdAt) {
}
