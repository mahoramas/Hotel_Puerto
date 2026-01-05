package org.docencia.hotel.mapper.jpa;

import java.util.Set;

import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.mapper.nosql.GuestPreferencesMapper;
import org.docencia.hotel.persistence.jpa.entity.GuestEntity;
import org.docencia.hotel.persistence.nosql.document.GuestPreferencesDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
    GuestPreferencesMapper.class
})
public interface GuestMapper {

    @Mapping(target = "preference", source = "preference")
    @Mapping(target = "bookings", ignore = true)
    GuestEntity toEntity(Guest domain);

    @Mapping(target = "preference", source = "preference")
    @Mapping(target = "bookings", ignore = true)
    Guest toDomain(GuestEntity entity);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "preference", source = "guest")
    @Mapping(target = "bookings", ignore = true)
    Guest toDomain(GuestEntity entity, GuestPreferencesDocument guest);

    Set<Guest> toDomain(Set<GuestEntity> bookings);

    Set<GuestEntity> toEntity(Set<Guest> bookings);
}