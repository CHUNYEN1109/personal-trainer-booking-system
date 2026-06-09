package com.management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SlotRequest {
    private LocalDate date;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boolean isAvailable = true;
    private Long locationId;
}
