package com.management.mappers;

import com.management.dtos.ClientDto;
import com.management.dtos.UpdateRequest;
import com.management.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mapping(target = "bookings", ignore = true)
    Client toEntity(ClientDto dto);

    @Mapping(target = "requestTime", expression = "java(LocalDateTime.now())")
    ClientDto toDto(Client client);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bookings", ignore = true)
    void update(UpdateRequest request, @MappingTarget Client client);
}
