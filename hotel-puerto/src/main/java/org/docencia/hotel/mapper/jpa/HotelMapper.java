package org.docencia.hotel.mapper.jpa;

import java.util.Set;

import org.docencia.hotel.domain.model.Hotel;
import org.docencia.hotel.persistence.jpa.entity.HotelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    @Mapping(target = "rooms", ignore = true)
    HotelEntity toEntity(Hotel domain);

    @Mapping(target = "rooms", ignore = true)
    Hotel toDomain(HotelEntity entity);

    Set<Hotel> toDomain(Set<HotelEntity> hotels);

    Set<HotelEntity> toEntity(Set<Hotel> hotels);
}