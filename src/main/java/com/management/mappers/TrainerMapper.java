package com.management.mappers;

import com.management.dtos.TrainerDto;
import com.management.dtos.UpdateRequest;
import com.management.entities.Trainer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TrainerMapper {
    @Mapping(target = "slotTimes", ignore = true)
    @Mapping(target = "bookings", ignore = true)
    Trainer toEntity(TrainerDto dto);

    @Mapping(target = "requestTime", expression = "java(LocalDateTime.now())")
    TrainerDto toDto(Trainer trainer);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slotTimes", ignore = true)
    @Mapping(target = "bookings", ignore = true)
    void update(UpdateRequest request, @MappingTarget Trainer trainer);
}
