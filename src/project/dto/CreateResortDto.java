package project.dto;

import java.time.Instant;
import java.util.List;

public record CreateResortDto(String name, long userId, List<Integer> townIds, Instant createdAt) {
}
