package com.reel.reserve.dto;

import com.reel.reserve.models.MovieScreening;
import com.reel.reserve.models.Seat;
import com.reel.reserve.models.User;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BookingsDTO {
    @NotNull(message = "Customer ID must not be null.")
    private Long userId;
    @NotNull(message = "Seat IDs must not be null.")
    private List<Long> seatIds;
    @NotNull(message = "Screening ID Event must not be null.")
    private Long screeningId;
}
