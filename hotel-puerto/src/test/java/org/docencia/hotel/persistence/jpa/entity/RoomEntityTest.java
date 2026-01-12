package org.docencia.hotel.persistence.jpa.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

class RoomEntityTest {

    @Test
    void testConstructorsAndAccessors() {
        RoomEntity entity = new RoomEntity();
        entity.setId(1L);
        entity.setNumber("202");
        entity.setType("Single");
        entity.setPricePerNight(50.0f);
        HotelEntity hotel = new HotelEntity();
        entity.setHotel(hotel);
        Set<BookingEntity> bookings = new HashSet<>();
        entity.setBookings(bookings);

        assertEquals(1L, entity.getId());
        assertEquals("202", entity.getNumber());
        assertEquals("Single", entity.getType());
        assertEquals(50.0f, entity.getPricePerNight());
        assertEquals(hotel, entity.getHotel());
        assertEquals(bookings, entity.getBookings());

        RoomEntity entity2 = new RoomEntity(1L, "202", "Single", 50.0f, hotel, bookings);
        assertEquals(entity, entity2);

        RoomEntity entity3 = new RoomEntity(1L);
        assertEquals(1L, entity3.getId());
    }
}
