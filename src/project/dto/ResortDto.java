package project.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record ResortDto(
        long id,
        String name,
        String description,
        String location,
        int townId,
        String howToGetThere,
        BigDecimal resortFee,
        BigDecimal cottageFee,
        BigDecimal poolFee,
        String resortImage,
        String poolImage,
        String cottageImage,
        List<RoomDto> roomDtos,
        String permitImage,
        Instant createdAt,
        Instant updatedAt) {

    public ResortDto() {
        this(
                0,
                "",
                "",
                "",
                0,
                "",
                new BigDecimal(0),
                new BigDecimal(0),
                new BigDecimal(0),
                "",
                "",
                "", List.of(),
                null,
                null,
                null
        );
    }
}
