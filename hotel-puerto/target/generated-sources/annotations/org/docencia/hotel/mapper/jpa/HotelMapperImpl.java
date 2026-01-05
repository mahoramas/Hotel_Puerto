package org.docencia.hotel.mapper.jpa;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.docencia.hotel.domain.model.Hotel;
import org.docencia.hotel.persistence.jpa.entity.HotelEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-05T02:17:42+0000",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class HotelMapperImpl implements HotelMapper {

    @Override
    public HotelEntity toEntity(Hotel domain) {
        if ( domain == null ) {
            return null;
        }

        HotelEntity hotelEntity = new HotelEntity();

        hotelEntity.id( domain.getId() );
        hotelEntity.name( domain.getName() );
        hotelEntity.address( domain.getAddress() );

        return hotelEntity;
    }

    @Override
    public Hotel toDomain(HotelEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Hotel hotel = new Hotel();

        hotel.setId( entity.getId() );
        hotel.setName( entity.getName() );
        hotel.setAddress( entity.getAddress() );

        return hotel;
    }

    @Override
    public Set<Hotel> toDomain(Set<HotelEntity> bookings) {
        if ( bookings == null ) {
            return null;
        }

        Set<Hotel> set = new LinkedHashSet<Hotel>( Math.max( (int) ( bookings.size() / .75f ) + 1, 16 ) );
        for ( HotelEntity hotelEntity : bookings ) {
            set.add( toDomain( hotelEntity ) );
        }

        return set;
    }

    @Override
    public Set<HotelEntity> toEntity(Set<Hotel> bookings) {
        if ( bookings == null ) {
            return null;
        }

        Set<HotelEntity> set = new LinkedHashSet<HotelEntity>( Math.max( (int) ( bookings.size() / .75f ) + 1, 16 ) );
        for ( Hotel hotel : bookings ) {
            set.add( toEntity( hotel ) );
        }

        return set;
    }
}
