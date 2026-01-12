package org.docencia.hotel.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class RoomTest {

    @Test
    void testConstructorsAndAccessors() {
        Room room = new Room();
        room.setId(1L);
        room.setNumber("101");
        room.setType("Double");
        room.setPricePerNight(100.0f);
        Hotel hotel = new Hotel();
        room.setHotel(hotel);
        Set<Booking> bookings = new HashSet<>();
        room.setBookings(bookings);

        assertEquals(1L, room.getId());
        assertEquals("101", room.getNumber());
        assertEquals("Double", room.getType());
        assertEquals(100.0f, room.getPricePerNight());
        assertEquals(hotel, room.getHotel());
        assertEquals(bookings, room.getBookings());

        Room room2 = new Room(1L, "101", "Double", 100.0f, hotel, bookings);
        assertEquals(room, room2);

        Room room3 = new Room(1L);
        assertEquals(1L, room3.getId());
    }
}
