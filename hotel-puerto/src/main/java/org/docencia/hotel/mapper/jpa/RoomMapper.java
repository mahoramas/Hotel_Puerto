package org.docencia.hotel.mapper.jpa;

import java.util.Set;

import org.docencia.hotel.domain.model.Room;
import org.docencia.hotel.persistence.jpa.entity.RoomEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    @Mapping(target = "hotel", source = "hotel")
    @Mapping(target = "bookings", ignore = true)
    RoomEntity toEntity(Room domain);

    @Mapping(target = "hotel", source = "hotel")
    @Mapping(target = "bookings", ignore = true)
    Room toDomain(RoomEntity entity);

    Set<Room> toDomain(Set<RoomEntity> rooms);

    Set<RoomEntity> toEntity(Set<Room> rooms);
}