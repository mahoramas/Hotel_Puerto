package org.docencia.hotel.mapper.jpa;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.docencia.hotel.domain.model.Booking;
import org.docencia.hotel.domain.model.Guest;
import org.docencia.hotel.domain.model.GuestPreferences;
import org.docencia.hotel.domain.model.Room;
import org.docencia.hotel.persistence.jpa.entity.BookingEntity;
import org.docencia.hotel.persistence.jpa.entity.GuestEntity;
import org.docencia.hotel.persistence.jpa.entity.RoomEntity;
import org.docencia.hotel.persistence.nosql.document.GuestPreferencesDocument;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-09T02:08:34+0000",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class BookingMapperImpl implements BookingMapper {

    @Override
    public BookingEntity toEntity(Booking domain) {
        if ( domain == null ) {
            return null;
        }

        BookingEntity bookingEntity = new BookingEntity();

        bookingEntity.setRoomId( roomToRoomEntity( domain.getRoom() ) );
        bookingEntity.setGuestId( guestToGuestEntity( domain.getGuest() ) );
        bookingEntity.setId( domain.getId() );
        bookingEntity.setCheckIn( domain.getCheckIn() );
        bookingEntity.setCheckOut( domain.getCheckOut() );

        return bookingEntity;
    }

    @Override
    public Booking toDomain(BookingEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Booking booking = new Booking();

        booking.setRoom( roomEntityToRoom( entity.getRoomId() ) );
        booking.setGuest( guestEntityToGuest( entity.getGuestId() ) );
        booking.setId( entity.getId() );
        booking.setCheckIn( entity.getCheckIn() );
        booking.setCheckOut( entity.getCheckOut() );

        return booking;
    }

    @Override
    public Set<Booking> toDomain(Set<BookingEntity> bookings) {
        if ( bookings == null ) {
            return null;
        }

        Set<Booking> set = new LinkedHashSet<Booking>( Math.max( (int) ( bookings.size() / .75f ) + 1, 16 ) );
        for ( BookingEntity bookingEntity : bookings ) {
            set.add( toDomain( bookingEntity ) );
        }

        return set;
    }

    @Override
    public Set<BookingEntity> toEntity(Set<Booking> bookings) {
        if ( bookings == null ) {
            return null;
        }

        Set<BookingEntity> set = new LinkedHashSet<BookingEntity>( Math.max( (int) ( bookings.size() / .75f ) + 1, 16 ) );
        for ( Booking booking : bookings ) {
            set.add( toEntity( booking ) );
        }

        return set;
    }

    protected RoomEntity roomToRoomEntity(Room room) {
        if ( room == null ) {
            return null;
        }

        RoomEntity roomEntity = new RoomEntity();

        roomEntity.setId( room.getId() );
        roomEntity.setNumber( room.getNumber() );
        roomEntity.setType( room.getType() );
        roomEntity.setPricePerNight( room.getPricePerNight() );
        roomEntity.setHotel( room.getHotel() );
        roomEntity.setBookings( toEntity( room.getBookings() ) );

        return roomEntity;
    }

    protected GuestPreferencesDocument guestPreferencesToGuestPreferencesDocument(GuestPreferences guestPreferences) {
        if ( guestPreferences == null ) {
            return null;
        }

        GuestPreferencesDocument guestPreferencesDocument = new GuestPreferencesDocument();

        guestPreferencesDocument.setPreferredLanguage( guestPreferences.getPreferredLanguage() );
        guestPreferencesDocument.setNewsLetterOptIn( guestPreferences.getNewsLetterOptIn() );
        List<String> list = guestPreferences.getTags();
        if ( list != null ) {
            guestPreferencesDocument.setTags( new ArrayList<String>( list ) );
        }
        guestPreferencesDocument.setFavoriteRoomType( guestPreferences.getFavoriteRoomType() );
        guestPreferencesDocument.setNotes( guestPreferences.getNotes() );

        return guestPreferencesDocument;
    }

    protected GuestEntity guestToGuestEntity(Guest guest) {
        if ( guest == null ) {
            return null;
        }

        GuestEntity guestEntity = new GuestEntity();

        guestEntity.setId( guest.getId() );
        guestEntity.setFullName( guest.getFullName() );
        guestEntity.setEmail( guest.getEmail() );
        guestEntity.setPhone( guest.getPhone() );
        guestEntity.setBookings( toEntity( guest.getBookings() ) );
        guestEntity.setPreference( guestPreferencesToGuestPreferencesDocument( guest.getPreference() ) );

        return guestEntity;
    }

    protected Room roomEntityToRoom(RoomEntity roomEntity) {
        if ( roomEntity == null ) {
            return null;
        }

        Room room = new Room();

        room.setId( roomEntity.getId() );
        room.setNumber( roomEntity.getNumber() );
        room.setType( roomEntity.getType() );
        room.setPricePerNight( roomEntity.getPricePerNight() );
        room.setHotel( roomEntity.getHotel() );
        room.setBookings( toDomain( roomEntity.getBookings() ) );

        return room;
    }

    protected GuestPreferences guestPreferencesDocumentToGuestPreferences(GuestPreferencesDocument guestPreferencesDocument) {
        if ( guestPreferencesDocument == null ) {
            return null;
        }

        GuestPreferences guestPreferences = new GuestPreferences();

        guestPreferences.setPreferredLanguage( guestPreferencesDocument.getPreferredLanguage() );
        guestPreferences.setNewsLetterOptIn( guestPreferencesDocument.isNewsLetterOptIn() );
        List<String> list = guestPreferencesDocument.getTags();
        if ( list != null ) {
            guestPreferences.setTags( new ArrayList<String>( list ) );
        }
        guestPreferences.setFavoriteRoomType( guestPreferencesDocument.getFavoriteRoomType() );
        guestPreferences.setNotes( guestPreferencesDocument.getNotes() );

        return guestPreferences;
    }

    protected Guest guestEntityToGuest(GuestEntity guestEntity) {
        if ( guestEntity == null ) {
            return null;
        }

        Guest guest = new Guest();

        guest.setId( guestEntity.getId() );
        guest.setFullName( guestEntity.getFullName() );
        guest.setEmail( guestEntity.getEmail() );
        guest.setPhone( guestEntity.getPhone() );
        guest.setBookings( toDomain( guestEntity.getBookings() ) );
        guest.setPreference( guestPreferencesDocumentToGuestPreferences( guestEntity.getPreference() ) );

        return guest;
    }
}
