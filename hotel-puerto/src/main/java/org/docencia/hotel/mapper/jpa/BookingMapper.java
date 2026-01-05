package org.docencia.hotel.mapper.jpa;

import java.util.Set;

import org.docencia.hotel.domain.model.Booking;
import org.docencia.hotel.persistence.jpa.entity.BookingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(target = "room", source = "room")
    @Mapping(target = "guest", source = "guest")
    BookingEntity toEntity(Booking domain);

    @Mapping(target = "room", source = "room")
    @Mapping(target = "guest", source = "guest")
    Booking toDomain(BookingEntity entity);

    Set<Booking> toDomain(Set<BookingEntity> bookings);
    
    Set<BookingEntity> toEntity(Set<Booking> bookings);
}
