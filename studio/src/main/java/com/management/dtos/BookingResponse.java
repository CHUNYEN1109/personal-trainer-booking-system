package com.management.dtos;


import com.management.entities.LocationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {
    private Long id;

    private Long clientId;
    private String clientName;

    private Long trainerId;
    private String trainerName;

    private Long slotTimeId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private LocationType location;

    private String bookingStatus;
    private LocalDateTime bookingTime;
    private LocalDateTime createdAt;

}
