package com.management.mappers;
import com.management.dtos.BookingRequest;
import com.management.dtos.BookingResponse;
import com.management.entities.Bookings;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookingMapper {


    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "client.name", target = "clientName")

    @Mapping(source = "trainer.id", target = "trainerId")
    @Mapping(source = "trainer.name", target = "trainerName")

    @Mapping(source = "slotTime.id", target = "slotTimeId")

    @Mapping(source = "slotTime.startTime", target = "startTime")
    @Mapping(source = "slotTime.endTime", target = "endTime")
    @Mapping(source = "slotTime.location.location", target = "location")
    BookingResponse toDto(Bookings bookings );

}
