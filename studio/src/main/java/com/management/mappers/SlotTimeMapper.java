package com.management.mappers;

import com.management.dtos.SlotRequest;
import com.management.dtos.SlotResponse;
import com.management.entities.Locations;
import com.management.entities.SlotTime;
import com.management.entities.Trainer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SlotTimeMapper {
    // toEntity, toDto, update
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "booking", ignore = true)
    @Mapping(target = "trainer", source = "trainer")
    @Mapping(target = "location", source = "location")
    SlotTime toEntity(SlotRequest request, Trainer trainer, Locations location);

    @Mapping(target = "trainerId", source = "trainer.id")
    @Mapping(target = "locationId", source = "location.id")
    @Mapping(target = "bookingId", expression = "java(slotTime.getBooking() == null ? null : slotTime.getBooking().getId())")
    SlotResponse toResponse(SlotTime slotTime);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "booking", ignore = true)
    @Mapping(target = "trainer", source = "trainer")
    @Mapping(target = "location", source = "location")
    void updateEntity(
            SlotRequest request,
            Trainer trainer,
            Locations location,
            @MappingTarget SlotTime slotTime

    );
}
