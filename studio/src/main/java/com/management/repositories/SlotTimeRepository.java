package com.management.repositories;

import com.management.entities.SlotTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface SlotTimeRepository extends JpaRepository<SlotTime, Long>{
    boolean existsByTrainerIdAndLocationIdAndDateAndStartTimeAndEndTime(
            Long trainerId,
            Long locationId,
            LocalDate date,
            LocalDateTime startTime,
            LocalDateTime endTime
    );
}
