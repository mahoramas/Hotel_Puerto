package org.docencia.hotel.mapper.jpa;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.docencia.hotel.domain.model.Room;
import org.docencia.hotel.persistence.jpa.entity.RoomEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-09T00:41:23+0000",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class RoomMapperImpl implements RoomMapper {

    @Override
    public RoomEntity toEntity(Room domain) {
        if ( domain == null ) {
            return null;
        }

        RoomEntity roomEntity = new RoomEntity();

        roomEntity.setHotel( domain.getHotel() );
        roomEntity.setId( domain.getId() );
        roomEntity.setNumber( domain.getNumber() );
        roomEntity.setType( domain.getType() );
        roomEntity.setPricePerNight( domain.getPricePerNight() );

        return roomEntity;
    }

    @Override
    public Room toDomain(RoomEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Room room = new Room();

        room.setHotel( entity.getHotel() );
        room.setId( entity.getId() );
        room.setNumber( entity.getNumber() );
        room.setType( entity.getType() );
        room.setPricePerNight( entity.getPricePerNight() );

        return room;
    }

    @Override
    public Set<Room> toDomain(Set<RoomEntity> bookings) {
        if ( bookings == null ) {
            return null;
        }

        Set<Room> set = new LinkedHashSet<Room>( Math.max( (int) ( bookings.size() / .75f ) + 1, 16 ) );
        for ( RoomEntity roomEntity : bookings ) {
            set.add( toDomain( roomEntity ) );
        }

        return set;
    }

    @Override
    public Set<RoomEntity> toEntity(Set<Room> bookings) {
        if ( bookings == null ) {
            return null;
        }

        Set<RoomEntity> set = new LinkedHashSet<RoomEntity>( Math.max( (int) ( bookings.size() / .75f ) + 1, 16 ) );
        for ( Room room : bookings ) {
            set.add( toEntity( room ) );
        }

        return set;
    }
}
