package com.management.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.management.entities.Bookings;
import com.management.entities.SlotTime;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TrainerDto {
    private Long id;
    private String name;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime requestTime;
}
